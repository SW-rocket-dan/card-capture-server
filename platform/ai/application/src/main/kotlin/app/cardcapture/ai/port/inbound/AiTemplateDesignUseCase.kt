package app.cardcapture.ai.port.inbound

import app.cardcapture.ai.model.TemplateLayoutPlan
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand

interface AiTemplateDesignUseCase {

    fun design(command: AiTemplateDesignCommand): TemplateLayoutPlan
}
