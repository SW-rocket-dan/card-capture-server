package app.cardcapture.template.adapter.inbound.web.dto

import app.cardcapture.template.application.port.inbound.dto.GenerateCardTemplateCommand

data class GenerateCardTemplateRequest(
    val purpose: String,
    val texts: List<String>,
    val color: String,
    val model: String,
    val prompt: String
) {

    fun toCommand() : GenerateCardTemplateCommand{
        return GenerateCardTemplateCommand(
            purpose = purpose,
            texts = texts,
            color = color,
            model = model,
            prompt = prompt
        )
    }
}
