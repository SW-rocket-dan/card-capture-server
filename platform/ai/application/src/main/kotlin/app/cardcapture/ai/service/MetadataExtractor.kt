package app.cardcapture.ai.service

import app.cardcapture.ai.domain.LlmRequest
import app.cardcapture.ai.domain.prompt.PromptId
import app.cardcapture.ai.domain.prompt.PromptLoader
import app.cardcapture.ai.model.TemplateDesignMetadata
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand
import app.cardcapture.ai.port.outbound.LlmSenderPort
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component

@Component
class MetadataExtractor(
    private val promptLoader: PromptLoader,
    private val objectMapper: ObjectMapper,
    private val llmSenderPort: LlmSenderPort
) {

    private val namespace: String = "templateDesign"
    private val name: String = "metadata"

    fun extract(aiTemplateDesignCommand: AiTemplateDesignCommand): TemplateDesignMetadata {

        val prompt = promptLoader.load(
            PromptId(
                namespace,
                name,
            ),
        )
        val userContent = buildString {
            appendLine("## Input")
            appendLine("Texts: ${aiTemplateDesignCommand.texts.joinToString(" | ")}")
            appendLine("Purpose: ${aiTemplateDesignCommand.purpose}")
            appendLine("Color: ${aiTemplateDesignCommand.color}")
            appendLine("User Prompt: ${aiTemplateDesignCommand.prompt}")
        }.trim()
        val request = LlmRequest(
            model = aiTemplateDesignCommand.llmModel,
            system = prompt.system,
            user = userContent,
        )
        val response = llmSenderPort.send(request)
        return objectMapper.readValue<TemplateDesignMetadata>(response.jsonValueRaw)
    }
}
