package app.cardcapture.template.adapter.outbound

import app.cardcapture.ai.model.TemplateLayoutPlanResult
import app.cardcapture.ai.port.inbound.AiTemplateDesignUseCase
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand
import app.cardcapture.template.application.model.*
import app.cardcapture.template.application.port.outbound.AiTemplateDesignPort
import app.cardcapture.template.application.port.outbound.dto.AiTemplateDesignPortCommand
import org.springframework.stereotype.Component


@Component
class AiTemplateDesignAdapter(
    private val aiTemplateDesignUseCase: AiTemplateDesignUseCase
) : AiTemplateDesignPort {

    override fun design(prompt: AiTemplateDesignPortCommand): AiTemplateDesignResult {
        val design: TemplateLayoutPlanResult = aiTemplateDesignUseCase.design(
            AiTemplateDesignCommand.ofOrThrow(
                llmModel = prompt.llmModel,
                imageModel = prompt.model,
                purpose = prompt.purpose,
                texts = prompt.texts,
                prompt = prompt.prompt,
                color = prompt.color
            )
        )

        return design.toTemplateResult()
    }

    private fun TemplateLayoutPlanResult.toTemplateResult(): AiTemplateDesignResult {
        val bg = when (background.mode.uppercase()) {
            "IMAGE" -> BackgroundPlanDto.image(
                prompt = requireNotNull(background.prompt),
                opacity = background.opacity
            )

            "COLOR" -> BackgroundPlanDto.color(
                colorHex = requireNotNull(background.colorHex) { "COLOR mode background colorHex is null" },
                opacity = background.opacity
            )

            else -> error("Unknown background mode: ${background.mode}")
        }

        val layers = layers.map { layer ->
            when (layer.type.lowercase()) {
                "text" -> PlannedTextLayerDto(
                    id = layer.id,
                    position = layer.position.toDto(),
                    text = requireNotNull(layer.content) { "text layer content is null" },
                    font = requireNotNull(layer.font) { "text layer font is null" },
                    size = requireNotNull(layer.size) { "text layer size is null" }
                )

                "image" -> PlannedImageLayerDto(
                    id = layer.id,
                    position = layer.position.toDto(),
                    url = null,
                    generate = true,
                    prompt = requireNotNull(layer.prompt) { "image layer prompt is null" }
                )

                else -> error("Unknown layer type: ${layer.type}")
            }
        }

        return AiTemplateDesignResult(
            background = bg,
            layers = layers
        )
    }

    private fun TemplateLayoutPlanResult.LayoutPosition.toDto(): PositionDto =
        PositionDto(x, y, width, height, rotate, zIndex, opacity)
}
