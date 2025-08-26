package app.api.member.application.service

import app.api.member.application.port.inbound.DevelopmentLoginUseCase
import app.api.member.application.port.outbound.IssueTokenPort
import app.api.member.application.port.outbound.LoadUserPort
import app.api.member.domain.Member
import app.api.member.domain.Provider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class DevelopmentLoginServiceTest(

) {

    private val loadUserPort: LoadUserPort = mockk<LoadUserPort>()
    private val issueTokenPort: IssueTokenPort = mockk<IssueTokenPort>()
    private val sut: DevelopmentLoginUseCase = DevelopmentLoginService(loadUserPort, issueTokenPort)


    @Test
    fun `등록된 사용자면 토큰을 발급한다`() {
        val oauthId = "test-user-id"
        val member = Member(id = 1L, provider = Provider.GOOGLE, oauthId = oauthId)

        every { loadUserPort.findByOauthIdAndProvider(oauthId, any()) } returns member
        every { issueTokenPort.issue(member) } returns "token"

        val token = sut.login(oauthId)

        assertThat(token).isEqualTo("token")
        verify {
            loadUserPort.findByOauthIdAndProvider(oauthId, Provider.GOOGLE)
            issueTokenPort.issue(member)
        }
    }


    @Test
    fun `등록되지 않은 사용자는 예외를 반환한다`() {
        val oauthId = "unknown"
        every { loadUserPort.findByOauthIdAndProvider(oauthId, Provider.GOOGLE) } returns null

        assertThatThrownBy { sut.login(oauthId) }.isInstanceOf(IllegalArgumentException::class.java)
        verify {
            loadUserPort.findByOauthIdAndProvider(oauthId, Provider.GOOGLE)
        }
        verify(exactly = 0) { issueTokenPort.issue(any()) }
    }
}
