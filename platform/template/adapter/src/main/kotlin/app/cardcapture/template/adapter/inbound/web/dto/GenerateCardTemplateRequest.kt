package app.cardcapture.template.adapter.inbound.web.dto

data class GenerateCardTemplateRequest(
    val model: String,
    val color: String,
    val texts: List<String>,
    val purpose: String
) {
}
