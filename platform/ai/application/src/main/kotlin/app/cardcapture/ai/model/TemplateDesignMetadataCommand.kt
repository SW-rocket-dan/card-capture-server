package app.cardcapture.ai.model

data class TemplateDesignMetadataCommand(
    val texts: List<String>,
    val purpose: String,
    val color: String,
    val userPrompt: String
) {
}
