package app.api.member.adapter.outbound.persistence

import app.api.member.application.port.outbound.LoadMemberPort
import app.api.member.domain.Member
import app.api.member.domain.OAuthProvider
import org.springframework.stereotype.Repository

@Repository
class MemberPersistenceAdapter(
    private val japMemberRepository: JpaMemberRepository
) : LoadMemberPort {

    override fun findByOauthIdAndProvider(oauthId: String, oauthProvider: OAuthProvider): Member? {
        return japMemberRepository.findByOauthIdAndOauthProvider(
            oauthId = oauthId,
            oauthProvider = JpaOAuthProvider.fromDomain(oauthProvider),
        )
            ?.let { jpaMember -> jpaMember.toDomain() }
    }

}
