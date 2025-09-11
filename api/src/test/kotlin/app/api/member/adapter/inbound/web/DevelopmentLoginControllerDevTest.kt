package app.api.member.adapter.inbound.web

import app.api.member.application.service.DevelopmentLoginService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ActiveProfiles("dev")
@WebMvcTest(controllers = [DevelopmentLoginController::class])
@AutoConfigureMockMvc(addFilters = false) // 시큐리티 필터 끔
class DevelopmentLoginControllerDevTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: com.fasterxml.jackson.databind.ObjectMapper
) {
    @MockkBean
    lateinit var developmentLoginService: DevelopmentLoginService

    @Test
    fun `dev 환경에서 로그인 성공`() {
        val body = mapOf("oauthId" to "test-user")
        every { developmentLoginService.login("test-user") } returns "fake-token"

        mockMvc.post("/api/v1/dev/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(body)
        }.andExpect {
            status { isOk() }
            jsonPath("$.accessToken") { value("fake-token") } // 컨트롤러 응답 필드명 확인!
        }
    }
}
