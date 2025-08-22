package app.api.member.adapter.`in`.web

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

class DevLoginControllerProfileTests {

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    @ActiveProfiles("dev")
    inner class DevProfile(
        @Autowired private val mockMvc : MockMvc
    ) {
        @Test
        fun `dev 환경에서 로그인 성공`(){
            mockMvc.post("/api/v1/dev/auth/login") {
                contentType = MediaType.APPLICATION_JSON
                content = """
                {"oauthId":"test-user"}
            """.trimIndent()
            }.andExpect {
                status { isOk() }
                jsonPath("$.accessToken") { exists() }
            }
        }
    }


    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    @ActiveProfiles("prod")
    inner class ProdProfile(
        @Autowired private val mockMvc : MockMvc
    ) {
        @Test
        fun `prod 환경에서 로그인 실패`(){
            mockMvc.post("/api/v1/dev/auth/login") {
                contentType = MediaType.APPLICATION_JSON
                content = """
                {"oauthId":"test-user"}
            """.trimIndent()
            }.andExpect {
                status { isNotFound() }
            }
        }
    }

}
