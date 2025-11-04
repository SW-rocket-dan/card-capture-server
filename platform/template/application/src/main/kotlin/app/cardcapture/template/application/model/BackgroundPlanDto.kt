package app.cardcapture.template.application.model

data class BackgroundPlanDto(
    val mode: BackgroundModeDto,
    val colorHex: String? = null,
    val url: String? = null,
    val prompt: String? = null,
    val opacity: Int = 100
) {
}
