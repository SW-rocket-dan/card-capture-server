package app.cardcapture.member.adapter.outbound.persistence

import app.cardcapture.member.domain.OAuthProvider


enum class JpaOAuthProvider {
    GOOGLE
    ;


    fun toDomain(): OAuthProvider {
        return OAuthProvider.valueOf(this.name)
    }

    companion object {
        fun fromDomain(domain: OAuthProvider): JpaOAuthProvider =
            valueOf(domain.name)
    }

}
