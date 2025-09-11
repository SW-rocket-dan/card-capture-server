package app.api.member.adapter.inbound.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ActiveProfiles("prod")
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class DevelopmentLoginControllerProdTest(
    @Autowired private val mockMvc: MockMvc
) {
    @Test
    fun `prod 환경에서 로그인 실패(404)`() {
        mockMvc.post("/api/v1/dev/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = """{"oauthId":"test-user"}"""
        }.andExpect {
            status { isNotFound() }
        }
    }
}
