package app.api.member.application.port.out

import app.api.member.domain.Member
import app.api.member.domain.Provider

interface LoadUserPort {

    fun findByOauthIdAndProvider(oauthId: String, provider: Provider): Member?

}
