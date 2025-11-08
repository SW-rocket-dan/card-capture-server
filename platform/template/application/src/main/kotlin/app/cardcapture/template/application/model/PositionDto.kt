package app.cardcapture.template.application.model

data class PositionDto(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val rotate: Int = 0,
    val zIndex: Int = 0,
    val opacity: Int = 100
)

