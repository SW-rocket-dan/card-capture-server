package app.cardcapture.lib.security

import app.cardcapture.lib.contracts.auth.CurrentMember
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityCurrentMember : CurrentMember {

    override val id: Long
        get() {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication == null || !authentication.isAuthenticated || authentication.principal is String && authentication.principal == "anonymousUser") {
                throw IllegalStateException("인증된 사용자 정보를 찾을 수 없습니다.")
            }
            return authentication.name.toLong()
        }
}
