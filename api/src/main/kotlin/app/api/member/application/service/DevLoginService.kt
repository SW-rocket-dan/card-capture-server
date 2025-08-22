package app.api.member.application.service

import app.api.member.application.port.`in`.DevLoginUseCase
import app.api.member.application.port.out.IssueTokenPort
import app.api.member.application.port.out.LoadUserPort
import app.api.member.domain.Provider
import org.springframework.stereotype.Service

@Service
class DevLoginService(
    private val loadUserPort: LoadUserPort,
    private val issueTokenPort: IssueTokenPort,
) : DevLoginUseCase {

    override fun login(oauthId: String): String {
        val user = loadUserPort.findByOauthIdAndProvider(oauthId, Provider.GOOGLE)
            ?: throw IllegalArgumentException("등록되지 않은 사용자입니다.")
        return issueTokenPort.issue(user)
    }
}
