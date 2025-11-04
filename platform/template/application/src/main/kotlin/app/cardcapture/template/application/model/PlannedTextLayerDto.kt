package app.cardcapture.template.application.model

data class PlannedTextLayerDto(
    override val id: Int,
    override val position: PositionDto,
    val text: String,
    val font: String,
    val size: String
) : PlannedLayerDto {
}
