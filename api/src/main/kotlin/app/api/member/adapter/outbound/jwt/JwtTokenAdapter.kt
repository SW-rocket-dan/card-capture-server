package app.api.member.adapter.outbound.jwt

import app.api.member.application.port.outbound.IssueTokenPort
import app.api.member.domain.Member
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

    override fun issue(member: Member): String {
        val now = Instant.now()
        val exp = now.plus(jwtProperties.accessTtl)
        return Jwts.builder()
            .issuer(jwtProperties.issuer)
            .subject(member.id.toString())
            .issuedAt(Date.from(now))
            .expiration(Date.from(exp))
            .signWith(accessKey)
            .compact()
    }


}
