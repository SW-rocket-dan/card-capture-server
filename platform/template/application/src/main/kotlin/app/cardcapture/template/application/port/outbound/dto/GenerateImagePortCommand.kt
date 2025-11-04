package app.cardcapture.template.application.port.outbound.dto

data class GenerateImagePortCommand(
    val prompt: String,
    val aspect: String = "1:1",
    val model: String
) {
}
