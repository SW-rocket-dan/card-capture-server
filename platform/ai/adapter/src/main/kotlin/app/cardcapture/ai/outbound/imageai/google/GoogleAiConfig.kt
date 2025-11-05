package app.cardcapture.ai.outbound.imageai.google

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class GoogleAiConfig (
    @Value("\${google.key}") private val apiKey: String
){

    private val baseUrl = "https://generativelanguage.googleapis.com"
    private val keyHeader = "x-goog-api-key"

    @Bean
    fun googleImageWebClient(): WebClient{
        val strategies = ExchangeStrategies.builder()
            .codecs { config ->
                config.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
            }
            .build()

        return WebClient.builder()
            .defaultHeader(keyHeader, apiKey)
            .baseUrl(baseUrl)
            .exchangeStrategies(strategies)
            .build()
    }
}
