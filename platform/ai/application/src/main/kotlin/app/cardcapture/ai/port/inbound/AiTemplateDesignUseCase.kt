package app.cardcapture.ai.port.inbound

import app.cardcapture.ai.model.TemplateLayoutPlanResult
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand

interface AiTemplateDesignUseCase {

    fun design(command: AiTemplateDesignCommand): TemplateLayoutPlanResult
}
