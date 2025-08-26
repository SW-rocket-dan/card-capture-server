package app.api.member.application.service

import app.api.member.application.port.inbound.DevelopmentLoginUseCase
import app.api.member.application.port.outbound.IssueTokenPort
import app.api.member.application.port.outbound.LoadMemberPort
import app.api.member.domain.OAuthProvider
import org.springframework.stereotype.Service

@Service
class DevelopmentLoginService(
    private val loadMemberPort: LoadMemberPort,
    private val issueTokenPort: IssueTokenPort,
) : DevelopmentLoginUseCase {

    override fun login(oauthId: String): String {
        val user = loadMemberPort.findByOauthIdAndProvider(oauthId, OAuthProvider.GOOGLE)
            ?: throw IllegalArgumentException("등록되지 않은 사용자입니다.")
        return issueTokenPort.issue(user)
    }
}
