package app.api.member.adapter.outbound.persistence

import app.api.member.application.port.outbound.LoadUserPort
import app.api.member.domain.Member
import app.api.member.domain.Provider
import org.springframework.stereotype.Service

@Service
class MemoryRepositoryAdapter : LoadUserPort {

    private val store = mutableMapOf<String, Member>()

    init {
        store["test-user"] = Member(
            id = 1L,
            oauthId = "test-user",
            provider = Provider.GOOGLE
        )
    }

    override fun findByOauthIdAndProvider(oauthId: String, provider: Provider): Member? {
        return store[oauthId]
    }
}
