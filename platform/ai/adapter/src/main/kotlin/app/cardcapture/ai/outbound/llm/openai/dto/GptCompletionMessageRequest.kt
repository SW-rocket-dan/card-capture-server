package app.cardcapture.ai.outbound.llm.openai.dto

data class GptCompletionMessageRequest(
    val role : GptMessageRole,
    val content: String,
) {
}
