package app.cardcapture.auth.adapter.outbound.jwt

import app.cardcapture.auth.application.port.outbound.IssueTokenPort
import app.cardcapture.auth.domain.AuthMember
import app.cardcapture.lib.jwt.JwtIssuer
import org.springframework.stereotype.Service

@Service
class TokenAdapter(
    private val jwtIssuer: JwtIssuer,
) : IssueTokenPort {

    override fun issue(member: AuthMember): String {
        val claims = mutableMapOf<String, Any>(
            "sub" to member.id.toString(),
        )
        return jwtIssuer.issue(claims)
    }

}
