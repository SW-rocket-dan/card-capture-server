package app.cardcapture.auth.adapter.outbound.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration


@ConfigurationProperties(prefix = "auth.jwt")
data class JwtProperties(
    val issuer: String,
    val accessTokenExpiration: Duration,
    val secretKey: String
)
