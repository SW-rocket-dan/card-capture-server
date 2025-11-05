package app.cardcapture.ai.port.inbound

import app.cardcapture.ai.port.inbound.dto.AiImageGenerateCommand

interface AiImageGenerateUseCase {

    fun generate(command: AiImageGenerateCommand): String
}
