package app.api.member.application.port.outbound

import app.api.member.domain.Member
import app.api.member.domain.Provider

interface LoadMemberPort {

    fun findByOauthIdAndProvider(oauthId: String, provider: Provider): Member?

}
