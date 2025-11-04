package app.cardcapture.ai.domain

enum class LlmModel(val model : String) {
    GPT("gpt-4o"), GEMINI("gemini-2.5-preview");

    companion object {
        fun from(name: String): LlmModel =
            entries.find { it.name.equals(name, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unsupported LlmModel: $name")
    }
}
