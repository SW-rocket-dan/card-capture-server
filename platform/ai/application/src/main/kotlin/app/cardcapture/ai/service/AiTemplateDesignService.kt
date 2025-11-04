package app.cardcapture.ai.service

import app.cardcapture.ai.model.TemplateLayoutPlan
import app.cardcapture.ai.port.inbound.AiTemplateDesignUseCase
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand
import org.springframework.stereotype.Service

@Service
class AiTemplateDesignService(
    private val metadataExtractor: MetadataExtractor,
    private val layoutPlanner: LayoutPlanner
) : AiTemplateDesignUseCase {

    override fun design(command: AiTemplateDesignCommand): TemplateLayoutPlan {
        val metadata = metadataExtractor.extract(command)

        val result = layoutPlanner.plan(metadata, command.llmModel)
        return result
    }
}
