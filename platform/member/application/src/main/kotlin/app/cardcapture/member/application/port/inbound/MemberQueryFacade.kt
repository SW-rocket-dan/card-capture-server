package app.cardcapture.member.application.port.inbound

import app.cardcapture.member.application.port.inbound.dto.AuthMemberView

interface MemberQueryFacade {

    fun findAuthMemberByOAuth(oauthId: String, oauthProvider: String): AuthMemberView
}
