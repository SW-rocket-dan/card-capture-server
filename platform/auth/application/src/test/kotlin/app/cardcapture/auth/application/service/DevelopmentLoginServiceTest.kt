package app.cardcapture.auth.application.service

import app.cardcapture.auth.application.port.inbound.DevelopmentLoginUseCase
import app.cardcapture.auth.application.port.outbound.IssueTokenPort
import app.cardcapture.auth.application.port.outbound.LoadAuthMemberPort
import app.cardcapture.auth.domain.AuthMember
import app.cardcapture.auth.domain.OAuthProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class DevelopmentLoginServiceTest(

) {

    private val loadMemberPort: LoadAuthMemberPort = mockk<LoadAuthMemberPort>()
    private val issueTokenPort: IssueTokenPort = mockk<IssueTokenPort>()
    private val sut: DevelopmentLoginUseCase = DevelopmentLoginService(loadMemberPort, issueTokenPort)


    @Test
    fun `등록된 사용자면 토큰을 발급한다`() {
        val oauthId = "test-user-id"
        val member = AuthMember(id = 1L, oauthProvider = OAuthProvider.GOOGLE, oauthId = oauthId)

        every { loadMemberPort.loadMemberByOAuth(oauthId, any()) } returns member
        every { issueTokenPort.issue(member) } returns "token"

        val token = sut.login(oauthId)


        assertThat(token).isEqualTo("token")
        verify {
            loadMemberPort.loadMemberByOAuth(oauthId, OAuthProvider.GOOGLE.name)
            issueTokenPort.issue(member)
        }
    }



}
