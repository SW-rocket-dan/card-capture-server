package app.cardcapture.ai.outbound.llm.openai

import app.cardcapture.ai.domain.LlmRequest
import app.cardcapture.ai.domain.LlmResponse
import app.cardcapture.ai.outbound.llm.openai.dto.GptCompletionMessageRequest
import app.cardcapture.ai.outbound.llm.openai.dto.GptCompletionRequest
import app.cardcapture.ai.outbound.llm.openai.dto.GptMessageRole
import app.cardcapture.ai.outbound.llm.openai.dto.GptResponseFormat
import app.cardcapture.ai.port.outbound.LlmSenderPort
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@Component
class LlmSenderAdapter(
    private val openAiWebClient: WebClient,
    private val objectMapper: ObjectMapper
) : LlmSenderPort {

    override fun send(request: LlmRequest): LlmResponse {


        val request = GptCompletionRequest(
            model = request.model.model,
            stream = false,
            messages = listOf(
                GptCompletionMessageRequest(
                    role = GptMessageRole.SYSTEM,
                    content = request.system,
                ),
                GptCompletionMessageRequest(
                    role = GptMessageRole.USER,
                    content = request.user,
                ),
            ),
            response_format = GptResponseFormat(
                type = "json_object",
            ),
        )

        val result = openAiWebClient.post()
            .uri("/chat/completions")
            .bodyValue(request)
            .retrieve()
            .onStatus(HttpStatusCode::isError) { res ->
                res.bodyToMono(String::class.java).flatMap { body ->
                    val msg = "Open AI error : ${res.statusCode()}  body = $body"
                    Mono.error(IllegalStateException(msg))
                }
            }
            .bodyToMono(String::class.java)
            .block()
        val content = objectMapper.readTree(result)["choices"][0]["message"]["content"].asText()

        /*
        *  TODO: content error handling ( empty value )
        */

        return LlmResponse(
            jsonValueRaw = content,
            resourceUsage = null,
        )
    }
}
