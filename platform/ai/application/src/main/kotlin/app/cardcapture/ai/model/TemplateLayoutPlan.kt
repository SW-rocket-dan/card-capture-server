package app.cardcapture.ai.model

data class TemplateLayoutPlan(
    val background: LayoutBackground,
    val layers: List<LayoutLayer>
) {

    data class LayoutBackground(
        val mode: String,      // "COLOR" or "IMAGE"
        val colorHex: String?,
        val prompt: String?,
        val opacity: Int
    )

    data class LayoutPosition(
        val x: Int,
        val y: Int,
        val width: Int,
        val height: Int,
        val rotate: Int,
        val zIndex: Int,
        val opacity: Int
    )

    /**
     * LLM이 주는 레이어를 그대로 받는 단일 DTO
     * - type = "text" or "image"
     * - text인 경우: role/content/font/size 필수, concept/prompt는 null
     * - image인 경우: concept/prompt 필수, role/content/font/size는 null
     */
    data class LayoutLayer(
        val id: Int,
        val type: String,          // "text" or "image"

        // text 전용 필드
        val role: String? = null,  // "headline" | "body" | ...
        val content: String? = null,
        val font: String? = null,
        val size: String? = null,

        // image 전용 필드
        val concept: String? = null,
        val prompt: String? = null,   // 이미지 생성용 프롬프트

        // 공통
        val position: LayoutPosition
    )
}
