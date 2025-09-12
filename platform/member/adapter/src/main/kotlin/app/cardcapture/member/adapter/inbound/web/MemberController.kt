package app.cardcapture.member.adapter.inbound.web

import app.cardcapture.member.application.port.inbound.MemberQueryFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberQueryFacade: MemberQueryFacade
) {

    @GetMapping("/me")
    fun findMyInfo(): ResponseEntity<Map<String, Any>> {
        val response = memberQueryFacade.getMyInfo()
        return ResponseEntity.ok(response)
    }
}