package app.cardcapture.ai.domain

data class LlmResponse(
    val jsonValueRaw: String,
    val resourceUsage: String?
) {

}
