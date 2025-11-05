package app.cardcapture.template.application.port.inbound.dto

data class GenerateCardTemplateCommand (
    val purpose: String,
    val texts: List<String>,
    val color: String,
    val model: String,
    val prompt: String,
){
}
