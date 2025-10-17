package app.cardcapture.ai.port.inbound.dto

import app.cardcapture.ai.domain.ImageModel
import app.cardcapture.ai.domain.LlmModel

data class AiTemplateDesignCommand private constructor(
    val llmModel: LlmModel,
    val imageModel: ImageModel,
    val purpose: String,
    val texts: List<String>,
    val prompt: String,
) {

    companion object {

        fun ofOtThrow(
            llmModel: String,
            imageModel: String,
            purpose: String,
            texts: List<String>,
            prompt: String
        ): AiTemplateDesignCommand {

            return AiTemplateDesignCommand(
                llmModel = LlmModel.from(llmModel),
                imageModel = ImageModel.from(imageModel),
                purpose = purpose,
                texts = texts,
                prompt = prompt,
            )
        }
    }
}
