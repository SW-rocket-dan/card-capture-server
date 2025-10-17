package app.cardcapture.ai.service

import app.cardcapture.ai.port.inbound.AiTemplateDesignUseCase
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand
import org.springframework.stereotype.Service

@Service
class AiTemplateDesignService : AiTemplateDesignUseCase {

    override fun design(command: AiTemplateDesignCommand): String {
        TODO("Not yet implemented")
    }
}
