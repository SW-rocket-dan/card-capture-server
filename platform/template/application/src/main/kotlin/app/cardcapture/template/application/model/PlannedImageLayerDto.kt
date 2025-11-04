package app.cardcapture.template.application.model

data class PlannedImageLayerDto(
    override val id: Int,
    override val position: PositionDto,
    val url: String? = null,
    val generate: Boolean = false,
    val prompt: String
) : PlannedLayerDto {
}
