package app.cardcapture.template.application.port.inbound

import app.cardcapture.template.application.port.inbound.dto.GenerateCardTemplateCommand
import domain.Template

interface GenerateCardTemplateUseCase {

    fun generate(command : GenerateCardTemplateCommand): Template
}
