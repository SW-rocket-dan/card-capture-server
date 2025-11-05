package app.cardcapture.ai.outbound.imageai.google.dto

data class GeminiPreviewImageRequest(
    val contents: List<ImagerPartsBlock>
) {

    data class ImagerPartsBlock(
        val parts: List<TextBlock>
    )

    data class TextBlock(
        val text: String
    )
}
