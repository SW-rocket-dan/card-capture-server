package app.cardcapture.template.application.port.outbound.dto

data class AiTemplateDesignPortCommand(
    val purpose: String,
    val texts: List<String>,
    val color: String,
    val prompt: String,
    val model : String,
) {
}
