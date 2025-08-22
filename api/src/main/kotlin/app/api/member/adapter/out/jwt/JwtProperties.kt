package app.api.member.adapter.out.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration


@ConfigurationProperties(prefix = "auth.jwt")
data class JwtProperties(
    val issuer: String,
    val accessTtl: Duration,
    val secret: String
)
