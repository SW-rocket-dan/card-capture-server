package app.api.member.adapter.outbound.persistence

import app.api.member.application.port.outbound.LoadMemberPort
import app.api.member.domain.Member
import app.api.member.domain.OAuthProvider
import org.springframework.stereotype.Service

//@Service
class MemoryRepositoryAdapter : LoadMemberPort {

    private val store = mutableMapOf<String, Member>()


    override fun findByOauthIdAndProvider(oauthId: String, oauthProvider: OAuthProvider): Member? {
        return store[oauthId]
    }
}
