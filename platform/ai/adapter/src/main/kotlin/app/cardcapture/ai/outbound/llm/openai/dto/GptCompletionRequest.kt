package app.cardcapture.ai.outbound.llm.openai.dto

data class GptCompletionRequest(
    val messages: List<GptCompletionMessageRequest>,
    val model: String,
    val stream: Boolean,
    val response_format: GptResponseFormat
) {
}
