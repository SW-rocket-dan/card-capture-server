package app.cardcapture.auth.adapter.outbound.client

import app.cardcapture.auth.application.port.outbound.LoadAuthMemberPort
import app.cardcapture.auth.domain.AuthMember
import app.cardcapture.auth.domain.OAuthProvider
import app.cardcapture.member.application.port.inbound.MemberQueryFacade
import org.springframework.stereotype.Service

@Service
class MemberFacadeAdapter(
    private val memberQueryFacade: MemberQueryFacade,
) : LoadAuthMemberPort {

    override fun loadMemberByOAuth(oauthId: String, authProvider: String): AuthMember {
        val existMember = memberQueryFacade.findAuthMemberByOAuth(oauthId, authProvider)
        return AuthMember(
            id = existMember.id,
            oauthId = oauthId,
            oauthProvider = mapProvider(existMember.oauthProvider),
        )
    }

    // TODO: 다른 곳에서 사용하는 상황이 발생하면 분리
    private fun mapProvider(code: String): OAuthProvider = when (code.lowercase()) {
        "google" -> OAuthProvider.GOOGLE
        else -> throw IllegalArgumentException("지원하지 않는 OAuth Provider: $code")
    }
}
