package app.cardcapture.template.application.service

import app.cardcapture.template.application.model.*
import app.cardcapture.template.application.port.inbound.GenerateCardTemplateUseCase
import app.cardcapture.template.application.port.inbound.dto.GenerateCardTemplateCommand
import app.cardcapture.template.application.port.outbound.AiTemplateDesignPort
import app.cardcapture.template.application.port.outbound.GenerateImagePort
import app.cardcapture.template.application.port.outbound.dto.AiTemplateDesignPortCommand
import app.cardcapture.template.application.port.outbound.dto.GenerateImagePortCommand
import domain.EditorPayload
import domain.Template
import org.springframework.stereotype.Service

@Service
class GenerateCardTemplateService(
    private val aiTemplateDesignPort: AiTemplateDesignPort,
    private val generateImagePort: GenerateImagePort,
    private val editorJsonConverter: EditorJsonConverter
) : GenerateCardTemplateUseCase {

    override fun generate(command: GenerateCardTemplateCommand): Template {

        val planResult = aiTemplateDesignPort.design(
            AiTemplateDesignPortCommand(
                purpose = command.purpose,
                texts = command.texts,
                color = command.color,
                prompt = command.prompt,
                model = command.model,
            ),
        )

        val checkedPlanResult = planResult.copy(
            background = backgroundImageCheck(planResult.background, command.model),
            layers = layerImageCheck(planResult.layers, command.model),
        )


        return Template(
            id = 1L, // TODO: auto increment,
            userId = 1L, // TODO: security
            purpose = command.purpose,
            prompt = command.prompt,
            color = command.color,
            title = command.purpose,
            description = command.texts.firstOrNull() ?: "" ,
            texts = command.texts,
            fileUrl = null,
            editorPayload = EditorPayload(editorJsonConverter.toJson(checkedPlanResult))
        )
    }

    private fun backgroundImageCheck(backgroundPlan: BackgroundPlanDto, model: String): BackgroundPlanDto {
        if (backgroundPlan.mode == BackgroundModeDto.COLOR) {
            return backgroundPlan
        }

        val prompt = backgroundPlan.prompt ?: error("background image prompt가 필요합니다. ")

        val backGroundImageUrl = generateImagePort.generate(
            GenerateImagePortCommand(
                prompt = prompt,
                model = model,
            ),
        )
        return backgroundPlan.copy(
            url = backGroundImageUrl,
        )
    }

    private fun layerImageCheck(layers: List<PlannedLayerDto>, model: String): List<PlannedLayerDto> {
        return layers.map { layer ->
            when (layer) {
                is PlannedTextLayerDto -> layer
                is PlannedImageLayerDto -> {
                    if (!layer.generate) return@map layer
                    val prompt = layer.prompt
                    val img = generateImagePort.generate(
                        GenerateImagePortCommand(
                            prompt = prompt,
                            model = model,
                        ),
                    )
                    layer.copy(
                        url = img,
                        generate = false,
                    )
                }
            }
        }
    }
}
