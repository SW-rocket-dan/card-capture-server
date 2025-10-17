package app.cardcapture.template.application.service

import app.cardcapture.template.application.port.inbound.GenerateCardTemplateUseCase
import app.cardcapture.template.application.port.inbound.dto.GenerateCardTemplateCommand
import app.cardcapture.template.application.port.outbound.TemplateDesignPort
import domain.Template
import domain.TemplateDesignPrompt
import org.springframework.stereotype.Service

@Service
class GenerateCardTemplateService(
    private val templateDesignPort: TemplateDesignPort
): GenerateCardTemplateUseCase {

    override fun generate(command: GenerateCardTemplateCommand): Template {
        val prompt = TemplateDesignPrompt(
            purpose = command.purpose,
            texts = command.texts,
            color =  command.color,
            prompt = command.prompt,
            model = command.model
        )

        val templateDesign = templateDesignPort.design(prompt)
        TODO("Not yet implemented")
    }
}
