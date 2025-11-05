package app.cardcapture.template.adapter.outbound

import app.cardcapture.ai.port.inbound.AiImageGenerateUseCase
import app.cardcapture.ai.port.inbound.dto.AiImageGenerateCommand
import app.cardcapture.template.application.port.outbound.GenerateImagePort
import app.cardcapture.template.application.port.outbound.dto.GenerateImagePortCommand
import org.springframework.stereotype.Component

@Component
class GeneratedImageAdapter(
    private val aiImageGenerateUseCase: AiImageGenerateUseCase
) : GenerateImagePort {

    override fun generate(command: GenerateImagePortCommand): String {

        return aiImageGenerateUseCase.generate(
            AiImageGenerateCommand.ofOrThrow(
                prompt = command.prompt,
                model = command.model,
            ),
        )
    }
}
