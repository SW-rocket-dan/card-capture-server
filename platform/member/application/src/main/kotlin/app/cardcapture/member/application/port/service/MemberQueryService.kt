package app.cardcapture.member.application.port.service

import app.cardcapture.lib.contracts.auth.CurrentMember
import app.cardcapture.member.application.port.inbound.MemberQueryFacade
import app.cardcapture.member.application.port.inbound.dto.AuthMemberView
import app.cardcapture.member.application.port.outbound.LoadMemberPort
import app.cardcapture.member.domain.OAuthProvider
import org.springframework.stereotype.Service

@Service
class MemberQueryService(
    private val loadMemberPort: LoadMemberPort,
    private val currentMember: CurrentMember,
): MemberQueryFacade {

    override fun findAuthMemberByOAuth(oauthId: String, oauthProvider: String): AuthMemberView {
        val member = loadMemberPort.findByOAuth(oauthId, OAuthProvider.valueOf(oauthProvider.uppercase()))
            ?: throw IllegalArgumentException("등록되지 않은 사용자입니다.")
        return AuthMemberView(
            id = member.id,
            oauthId = member.oauthId,
            oauthProvider = member.oauthProvider.name
        )
    }

    override fun getMyInfo(): Map<String, Any> {
        val memberId = currentMember.id
        return mapOf("id" to memberId)
    }
}
