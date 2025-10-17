package app.cardcapture.template.adapter.outbound

import app.cardcapture.ai.port.inbound.AiTemplateDesignUseCase
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand
import app.cardcapture.template.application.port.outbound.TemplateDesignPort
import domain.TemplateDesignPrompt
import org.springframework.stereotype.Component


@Component
class AiTemplateDesignAdapter(
    private val aiTemplateDesignUseCase: AiTemplateDesignUseCase
) : TemplateDesignPort {

    override fun design(prompt: TemplateDesignPrompt): String {
        return aiTemplateDesignUseCase.design(AiTemplateDesignCommand.ofOtThrow(
            llmModel = "GPT",
            imageModel = prompt.model,
            purpose = prompt.purpose,
            texts = prompt.texts,
            prompt = prompt.prompt
        ))
    }
}
