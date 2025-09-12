package app.cardcapture.auth.adapter.outbound.jwt

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.charset.StandardCharsets
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtConfig {

    @Bean
    fun accessKey(properties: JwtProperties): SecretKey {
        val keyBytes = properties.secretKey.toByteArray(StandardCharsets.UTF_8)
        return SecretKeySpec(keyBytes, "HmacSHA256")
    }
}
