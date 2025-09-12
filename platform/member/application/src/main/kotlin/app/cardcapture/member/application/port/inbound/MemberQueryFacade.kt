package app.cardcapture.member.application.port.inbound

import app.cardcapture.member.application.port.inbound.dto.AuthMemberView

interface MemberQueryFacade {

    fun findAuthMemberByOAuth(oauthId: String, oauthProvider: String): AuthMemberView

    /*
    *  TODO: Member 조회 기능 추가시 리팩토링 예정
    */
    fun getMyInfo(): Map<String, Any>
}
