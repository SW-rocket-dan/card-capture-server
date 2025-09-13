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

    override fun loadMemberByOAuth(oauthId: String, oauthProvider: OAuthProvider): AuthMember {
        val existMember = memberQueryFacade.findAuthMemberByOAuth(oauthId, oauthProvider.name)
        return AuthMember(
            id = existMember.id,
            oauthId = oauthId,
            oauthProvider = mapProvider(existMember.oauthProvider),
        )
    }

    // TODO: 다른 곳에서 사용하는 상황이 발생하면 분리
    private fun mapProvider(code: String): OAuthProvider =
        OAuthProvider.entries.firstOrNull { it.name.equals(code, ignoreCase = true) }
            ?: throw IllegalArgumentException("지원하지 않는 OAuth Provider: $code")
}
