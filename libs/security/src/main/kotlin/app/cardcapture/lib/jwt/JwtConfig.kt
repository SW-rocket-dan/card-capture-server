package app.cardcapture.lib.jwt

import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import javax.crypto.SecretKey

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class JwtConfig(
    private val props: JwtProperties
) {

    @Bean
    fun accessKey(): SecretKey {
        val decoded = try {
            Decoders.BASE64.decode(props.secretKey)
        } catch (e: IllegalArgumentException) {
            throw IllegalStateException("auth.jwt.secret-key must be Base64-encoded.", e)
        }
        require(decoded.size >= 32) { "HS256 requires >= 256-bit key (Base64 decoded >= 32 bytes)." }
        return Keys.hmacShaKeyFor(decoded)
    }

    @Bean
    fun jwtIssuer(accessKey: SecretKey): JwtIssuer {
        return JwtIssuerImpl(props, accessKey)
    }

    @Bean
    fun jwtDecoder(accessKey: SecretKey): JwtDecoder =
        NimbusJwtDecoder.withSecretKey(accessKey)
            .macAlgorithm(org.springframework.security.oauth2.jose.jws.MacAlgorithm.HS256)
            .build()
}
