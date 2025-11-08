package app.cardcapture.template.application.model

data class BackgroundPlanDto private constructor(
    val mode: BackgroundModeDto,
    val colorHex: String? = null,
    val url: String? = null,
    val prompt: String? = null,
    val opacity: Int = 100
) {
    companion object {
        fun color(colorHex: String, opacity: Int = 100): BackgroundPlanDto {
            require(colorHex.isNotBlank()) { "colorHex must not be blank for COLOR mode" }
            return BackgroundPlanDto(
                mode = BackgroundModeDto.COLOR,
                colorHex = colorHex,
                url = null,
                prompt = null,
                opacity = opacity
            )
        }

        fun image( prompt: String, opacity: Int = 100): BackgroundPlanDto {
            require(prompt.isNotBlank()) { "url must not be blank for IMAGE mode" }
            return BackgroundPlanDto(
                mode = BackgroundModeDto.IMAGE,
                colorHex = null,
                prompt = prompt,
                opacity = opacity
            )
        }
    }

    init {
        when (mode) {
            BackgroundModeDto.COLOR -> require(url == null && prompt == null) {
                "For COLOR mode, url and prompt must be null"
            }
            BackgroundModeDto.IMAGE -> require(colorHex == null) {
                "For IMAGE mode, colorHex must be null"
            }
        }
    }
}
