package app.cardcapture.auth.application.port.outbound

import app.cardcapture.auth.domain.AuthMember
import app.cardcapture.auth.domain.OAuthProvider

interface LoadAuthMemberPort {

    fun loadMemberByOAuth(oauthId: String, oauthProvider: OAuthProvider) : AuthMember

}
