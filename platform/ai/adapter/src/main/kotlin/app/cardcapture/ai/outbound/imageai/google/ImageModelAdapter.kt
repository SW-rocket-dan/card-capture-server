package app.cardcapture.ai.outbound.imageai.google

import app.cardcapture.ai.outbound.imageai.google.dto.GeminiPreviewImageRequest
import app.cardcapture.ai.outbound.imageai.google.dto.GeminiPreviewImageResponse
import app.cardcapture.ai.port.outbound.ImageModelPort
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Component
class ImageModelAdapter(
    private val googleImageWebClient: WebClient,
) : ImageModelPort {

    override fun generate(prompt: String, model: String): ByteArray {

        val request = GeminiPreviewImageRequest(
            contents = listOf(
                GeminiPreviewImageRequest.ImagerPartsBlock(
                    parts = listOf(GeminiPreviewImageRequest.TextBlock(prompt)),
                ),
            ),
        )

        val response = googleImageWebClient.post()
            .uri("v1beta/models/gemini-2.5-flash-image-preview:generateContent")
            .bodyValue(request)
            .retrieve()
            .onStatus(HttpStatusCode::isError) { res ->
                res.bodyToMono(String::class.java).flatMap { body ->
                    val msg = "Gemini image API error: ${res.statusCode()} body=$body"
                    Mono.error(IllegalStateException(msg))
                }
            }
            .bodyToMono(GeminiPreviewImageResponse::class.java)
            .block() ?: error("Empty response from Gemini image API")


        val base64Data = response.candidates
            .firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull { it.inlineData != null }
            ?.inlineData
            ?.data
            ?: error("Image data missing")
        return Base64.getDecoder().decode(base64Data)
    }
}
