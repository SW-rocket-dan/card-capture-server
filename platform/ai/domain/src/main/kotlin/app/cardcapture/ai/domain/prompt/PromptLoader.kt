package app.cardcapture.ai.domain.prompt

interface PromptLoader {

    fun load(id: PromptId): PromptSpec
}
