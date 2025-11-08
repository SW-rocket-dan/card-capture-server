package app.cardcapture.template.application.port.outbound

import app.cardcapture.template.application.model.AiTemplateDesignResult
import app.cardcapture.template.application.port.outbound.dto.AiTemplateDesignPortCommand

interface  AiTemplateDesignPort {

    fun design(prompt: AiTemplateDesignPortCommand): AiTemplateDesignResult
}
