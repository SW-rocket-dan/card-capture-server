package app.cardcapture.auth.application.service

import app.cardcapture.auth.application.port.inbound.DevelopmentLoginUseCase
import app.cardcapture.auth.application.port.outbound.IssueTokenPort
import app.cardcapture.auth.application.port.outbound.LoadAuthMemberPort
import app.cardcapture.auth.domain.OAuthProvider
import org.springframework.stereotype.Service


@Service
class DevelopmentLoginService (
    private val loadAuthMemberPort: LoadAuthMemberPort,
    private val issueTokenPort: IssueTokenPort,
): DevelopmentLoginUseCase {

    override fun login(oauthId: String): String {
        val authMember = loadAuthMemberPort.loadMemberByOAuth(oauthId, OAuthProvider.GOOGLE)
        return issueTokenPort.issue(authMember)
    }
}
