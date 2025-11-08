package app.cardcapture.template.application.model

data class AiTemplateDesignResult(
    val background : BackgroundPlanDto,
    val layers: List<PlannedLayerDto>
){

}
