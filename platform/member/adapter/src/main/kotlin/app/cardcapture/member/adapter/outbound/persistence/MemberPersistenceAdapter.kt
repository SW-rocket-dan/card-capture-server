package app.cardcapture.member.adapter.outbound.persistence

import app.cardcapture.member.application.port.outbound.LoadMemberPort
import app.cardcapture.member.domain.Member
import app.cardcapture.member.domain.OAuthProvider
import org.springframework.stereotype.Repository

@Repository
class MemberPersistenceAdapter(
    private val memberJpaRepository: MemberJpaRepository
) : LoadMemberPort {

    override fun findByOAuth(oauthId: String, oauthProvider: OAuthProvider): Member? {
        return memberJpaRepository.findByOauthIdAndOauthProvider(
            oauthId = oauthId,
            oauthProvider = JpaOAuthProvider.fromDomain(oauthProvider),
        )
            ?.let { jpaMember -> jpaMember.toDomain() }
    }
}
