package app.cardcapture.member.application.port.outbound

import app.cardcapture.member.domain.Member
import app.cardcapture.member.domain.OAuthProvider

interface LoadMemberPort {

    fun findByOAuth(oauthId: String , oauthProvider: OAuthProvider): Member?
}
