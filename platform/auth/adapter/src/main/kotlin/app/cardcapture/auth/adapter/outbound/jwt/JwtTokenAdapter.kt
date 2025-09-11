package app.cardcapture.auth.adapter.outbound.jwt

import app.cardcapture.auth.application.port.outbound.IssueTokenPort
import app.cardcapture.auth.domain.AuthMember
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date

import javax.crypto.SecretKey

@Service
class JwtTokenAdapter(
    private val jwtProperties: JwtProperties,
    private val accessKey: SecretKey
) : IssueTokenPort {

    override fun issue(member: AuthMember): String {
        val now = Instant.now()
        val expiration = now.plus(jwtProperties.accessTokenExpiration)
        return Jwts.builder()
            .issuer(jwtProperties.issuer)
            .subject(member.id.toString())
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiration))
            .signWith(accessKey)
            .compact()
    }

}
