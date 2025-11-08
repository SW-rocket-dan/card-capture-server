package app.cardcapture.ai.service

import app.cardcapture.ai.port.inbound.AiImageGenerateUseCase
import app.cardcapture.ai.port.inbound.dto.AiImageGenerateCommand
import app.cardcapture.ai.port.outbound.ImageModelPort
import app.cardcapture.ai.port.outbound.ImageStoragePort
import org.springframework.stereotype.Service

@Service
class AiImageGenerateService(
    private val imageModelPort: ImageModelPort,
    private val imageStoragePort: ImageStoragePort
): AiImageGenerateUseCase {

    override fun generate(command: AiImageGenerateCommand): String {
        val byteArrays = imageModelPort.generate(command.prompt, command.model.name)
        return imageStoragePort.upload(byteArrays, "generate")
    }
}
