package app.cardcapture.ai.outbound.imageai.google.dto


data class GeminiPreviewImageResponse(
    val candidates: List<Candidate>,
    val modelVersion: String? = null
) {
    data class Candidate(
        val content: Content?
    )

    data class Content(
        val parts: List<Part>?
    )

    data class Part(
        val text: String? = null,
        val inlineData: InlineData? = null
    )

    data class InlineData(
        val mimeType: String? = null,
        val data: String? = null
    )
}
