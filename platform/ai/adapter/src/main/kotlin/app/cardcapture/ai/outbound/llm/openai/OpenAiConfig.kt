package app.cardcapture.ai.outbound.llm.openai

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class OpenAiConfig (
    @Value("\${openai.key}") private val apiKey: String
){

    private val baseUrl = "https://api.openai.com/v1"

    @Bean
    fun openAiWebClient(): WebClient {
        /*
        *  @TODO: exception Handling
        */
        return WebClient.builder()
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer $apiKey")
            .baseUrl(baseUrl)
            .build()
    }

}
