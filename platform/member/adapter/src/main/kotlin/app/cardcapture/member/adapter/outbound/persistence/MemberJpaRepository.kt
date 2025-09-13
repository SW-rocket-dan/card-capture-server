package app.cardcapture.member.adapter.outbound.persistence

import org.springframework.data.jpa.repository.JpaRepository


interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long> {

    fun findByOauthIdAndOauthProvider(oauthId: String, oauthProvider: JpaOAuthProvider): MemberJpaEntity?
}
