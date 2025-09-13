package app.cardcapture.lib.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {

        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.characterEncoding = "UTF-8"
        response.contentType = "application/json;charset=UTF-8"
        // 표준 Bearer 챌린지 헤더 (상황에 맞게 값 조정 가능)
        response.setHeader("WWW-Authenticate", """Bearer realm="api", error="unauthorized", error_description="Authentication required"""")
        if (response.isCommitted) return
        // TODO: 에러 응답 공통 포맷 적용 시 교체
        response.writer.use { it.write("""{"error":"로그인이 필요합니다"}""") }
    }
}
