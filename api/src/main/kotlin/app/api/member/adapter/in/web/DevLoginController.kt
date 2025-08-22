package app.api.member.adapter.`in`.web

import app.api.member.adapter.`in`.web.dto.DevLoginRequest
import app.api.member.adapter.`in`.web.dto.LoginResponse
import app.api.member.application.port.`in`.DevLoginUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DevLoginController(
    private val devLoginUseCase: DevLoginUseCase
) {

    @PostMapping("/api/v1/dev/auth/login")
    fun login(@RequestBody request: DevLoginRequest): ResponseEntity<LoginResponse> {
        val result = devLoginUseCase.login(request.oauthId);
        return ResponseEntity.ok()
            .body(LoginResponse.from(result))
    }
}
