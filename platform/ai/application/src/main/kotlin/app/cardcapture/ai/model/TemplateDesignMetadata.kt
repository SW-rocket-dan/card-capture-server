package app.cardcapture.ai.model

data class TemplateDesignMetadata(
    val purpose: String,
    val mood: String,
    val style: String,
    val background: BackgroundBlock,
    val texts: List<TextBlock>,
    val images: List<ImageBlock>,
    val colorScheme: ColorScheme,
    val constraints: List<String>
) {

    data class BackgroundBlock(
        val mode: String,
        val colorHex: String?,
        val prompt: String?,
        val opacity: Int
    )

    data class TextBlock(
        val role: String,         // "headline", "subtitle", "body", "caption", "cta" 등
        val emphasis: String,     // "high", "medium", "low"
        val priority: Int,
        val text: String,
        val placement: String, // 선택적 위치 힌트
        val font: String? = null,      // Quill 화이트리스트 중 하나
        val size: String? = null       // "1px" ~ "128px"
    )

    data class ImageBlock(
        val concept: String,
        val purpose: String,       // "main", "decorative", "icon" 등
        val importance: String,    // "high", "medium", "low"
        val placement: String// 선택적 위치 힌트
    )

    data class ColorScheme(
        val primary: String,
        val secondary: String,
        val text: String,
        val accent: String,
        val reasoning: String
    )
}
