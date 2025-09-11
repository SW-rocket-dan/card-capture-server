package app.cardcapture.auth.adapter.outbound.jwt

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.crypto.SecretKey

@Configuration
class JwtConfig {

    @Bean
    fun accessKey(properties: JwtProperties): SecretKey {
        val keyBytes = Decoders.BASE64.decode(properties.secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
