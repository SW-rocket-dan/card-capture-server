package app.cardcapture.ai.domain

enum class LlmModel {
    GPT, GEMINI;

    companion object {
        fun from(name: String): LlmModel =
            entries.find { it.name.equals(name, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unsupported LlmModel: $name")
    }
}
