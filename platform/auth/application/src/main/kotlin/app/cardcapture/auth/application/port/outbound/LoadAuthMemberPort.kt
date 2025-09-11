package app.cardcapture.auth.application.port.outbound

import app.cardcapture.auth.domain.AuthMember

interface LoadAuthMemberPort {

    fun loadMemberByOAuth(oauthId: String, authProvider: String) : AuthMember

}
