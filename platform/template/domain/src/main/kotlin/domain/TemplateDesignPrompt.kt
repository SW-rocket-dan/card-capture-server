package domain

data class TemplateDesignPrompt(
    val purpose: String,
    val texts: List<String>,
    val color: String,
    val prompt: String,
    val model : String,
) {
}
