package app.api.member.adapter.inbound.web

import app.api.member.application.service.DevelopmentLoginService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

class DevelopmentLoginControllerProfileTests {

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    @ActiveProfiles("dev")
    inner class DevProfile(
        @Autowired private val mockMvc : MockMvc,
        @Autowired private val objectMapper: com.fasterxml.jackson.databind.ObjectMapper
    ) {

        @MockkBean
        lateinit var developmentLoginService: DevelopmentLoginService

        @Test
        fun `dev 환경에서 로그인 성공`(){
            val body = mapOf("oauthId" to "test-user")

            every { developmentLoginService.login("test-user") } returns "fake-token"

            mockMvc.post("/api/v1/dev/auth/login") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(body)
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
        @Autowired private val mockMvc : MockMvc,
        @Autowired private val objectMapper: com.fasterxml.jackson.databind.ObjectMapper
    ) {

        @MockkBean
        lateinit var developmentLoginService: DevelopmentLoginService

        @Test
        fun `prod 환경에서 로그인 실패`(){
            val body = mapOf("oauthId" to "test-user")

            every { developmentLoginService.login("test-user") } returns "fake-token"

            mockMvc.post("/api/v1/dev/auth/login") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(body)
            }.andExpect {
                status { isNotFound() }
            }
        }
    }

}
