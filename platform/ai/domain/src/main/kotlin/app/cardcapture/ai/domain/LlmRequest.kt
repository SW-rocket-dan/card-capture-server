package app.cardcapture.ai.domain

data class LlmRequest (
    val model : LlmModel,
    val system: String,
    val user : String
) {
}
