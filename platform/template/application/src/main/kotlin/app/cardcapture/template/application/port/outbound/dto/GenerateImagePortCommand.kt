package app.cardcapture.template.application.port.outbound.dto

data class GenerateImagePortCommand(
    val prompt: String,
    val model: String
) {
}
