package app.api.member.adapter.outbound.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface JpaMemberRepository : JpaRepository<JpaMember, Long> {

    fun findByOauthIdAndOauthProvider(oauthId: String, oauthProvider: JpaOAuthProvider): JpaMember?
}
