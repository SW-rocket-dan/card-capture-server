package app.cardcapture.template.application.model


sealed interface PlannedLayerDto {
    val id : Int
    val position: PositionDto
}
