package app.cardcapture.ai.outbound.llm.openai.dto

import com.fasterxml.jackson.annotation.JsonValue

enum class GptMessageRole {
    SYSTEM,
    USER,
    ASSISTANT;

    @JsonValue
    override fun toString(): String {
        return name.lowercase()
    }
}
