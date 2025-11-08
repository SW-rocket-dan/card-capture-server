package app.cardcapture.ai.service

import app.cardcapture.ai.domain.LlmRequest
import app.cardcapture.ai.domain.prompt.PromptId
import app.cardcapture.ai.domain.prompt.PromptLoader
import app.cardcapture.ai.model.TemplateDesignMetadataCommand
import app.cardcapture.ai.model.TemplateDesignMetadataResult
import app.cardcapture.ai.port.inbound.dto.AiTemplateDesignCommand
import app.cardcapture.ai.port.outbound.LlmSenderPort
import com.fasterxml.jackson.core.JsonProcessingException
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

    fun extract(aiTemplateDesignCommand: AiTemplateDesignCommand): TemplateDesignMetadataResult {

        val prompt = promptLoader.load(
            PromptId(
                namespace,
                name,
            ),
        )

        val content = TemplateDesignMetadataCommand(
            texts = aiTemplateDesignCommand.texts,
            purpose = aiTemplateDesignCommand.purpose,
            color = aiTemplateDesignCommand.color,
            userPrompt = aiTemplateDesignCommand.prompt
        )

        val request = LlmRequest(
            model = aiTemplateDesignCommand.llmModel,
            system = prompt.system,
            user = objectMapper.writeValueAsString(content),
        )
        val response = llmSenderPort.send(request)
        try {
            return objectMapper.readValue<TemplateDesignMetadataResult>(response.jsonValueRaw)
        } catch (e: JsonProcessingException) {
            throw IllegalStateException("fail to llm send result")
        }
    }
}
