package app.cardcapture.lib.jwt

import io.jsonwebtoken.Jwts
import java.time.Instant
import java.util.Date
import javax.crypto.SecretKey

class JwtIssuerImpl(
    private val jwtProperties: JwtProperties,
    private val accessKey: SecretKey
) : JwtIssuer {

    override fun issue(claims: Map<String, Any>): String {
        val now = Instant.now()
        val sub = claims["sub"] as? String
            ?: throw IllegalArgumentException("Missing required claim: sub")
        val expiration = now.plus(jwtProperties.accessTokenExpiration)
        return Jwts.builder()
            .issuer(jwtProperties.issuer)
            .subject(sub)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiration))
            .signWith(accessKey)
            .compact()
    }
}
