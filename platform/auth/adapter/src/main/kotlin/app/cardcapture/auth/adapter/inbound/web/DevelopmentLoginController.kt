package app.cardcapture.auth.adapter.inbound.web

import app.cardcapture.auth.adapter.inbound.web.dto.DevelopmentLoginRequest
import app.cardcapture.auth.adapter.inbound.web.dto.LoginResponse
import app.cardcapture.auth.application.port.inbound.DevelopmentLoginUseCase
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Profile("local", "dev")
@RestController
class DevelopmentLoginController(
    private val developmentLoginUseCase: DevelopmentLoginUseCase
) {

    @PostMapping("/api/v1/dev/auth/login")
    fun login(@RequestBody request: DevelopmentLoginRequest): ResponseEntity<LoginResponse> {
        val result = developmentLoginUseCase.login(request.oauthId);
        return ResponseEntity.ok()
            .body(LoginResponse(result))
    }
}
