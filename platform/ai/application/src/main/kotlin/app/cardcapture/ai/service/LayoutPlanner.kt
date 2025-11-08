package app.cardcapture.ai.service

import app.cardcapture.ai.domain.LlmModel
import app.cardcapture.ai.domain.LlmRequest
import app.cardcapture.ai.domain.prompt.PromptId
import app.cardcapture.ai.domain.prompt.PromptLoader
import app.cardcapture.ai.model.TemplateDesignMetadataResult
import app.cardcapture.ai.model.TemplateLayoutPlanResult
import app.cardcapture.ai.port.outbound.LlmSenderPort
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component

@Component
class LayoutPlanner(
    private val promptLoader: PromptLoader,
    private val llmSenderPort: LlmSenderPort,
    private val objectMapper: ObjectMapper
) {

    private val namespace: String = "templateDesign"
    private val name: String = "layout"

    fun plan(metadata: TemplateDesignMetadataResult, model: LlmModel): TemplateLayoutPlanResult {
        val prompt = promptLoader.load(
            PromptId(
                namespace,
                name,
            ),
        )

        val request = LlmRequest(
            model = model,
            system = prompt.system,
            user = objectMapper.writeValueAsString(metadata),
        )


        val response = llmSenderPort.send(request)

        try {
            return objectMapper.readValue<TemplateLayoutPlanResult>(response.jsonValueRaw)
        } catch (e: JsonProcessingException) {
            throw IllegalStateException("fail to llm send result")
        }
    }
}
