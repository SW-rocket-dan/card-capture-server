package app.cardcapture.ai.port.inbound.dto

import app.cardcapture.ai.domain.ImageModel

data class AiImageGenerateCommand(
    val prompt: String,
    val model: ImageModel
) {

    companion object {
        fun ofOrThrow(
            prompt: String,
            model: String
        ): AiImageGenerateCommand {
            return AiImageGenerateCommand(
                prompt = prompt,
                model = ImageModel.from(model),
            )
        }
    }
}
