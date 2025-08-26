package app.api.member.application.port.outbound

import app.api.member.domain.Member
import app.api.member.domain.OAuthProvider

interface LoadMemberPort {

    fun findByOauthIdAndProvider(oauthId: String, oauthProvider: OAuthProvider): Member?

}
