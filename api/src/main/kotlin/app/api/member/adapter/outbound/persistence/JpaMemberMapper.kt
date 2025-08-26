package app.api.member.adapter.outbound.persistence

import app.api.member.domain.Member
import app.api.member.domain.OAuthProvider

class JpaMemberMapper {

    companion object {

        fun toDomainMember(japMember: JpaMember): Member {
            return Member(
                id = japMember.id,
                oauthProvider = toDomainOAuthProvider(japMember.oauthProvider),
                oauthId = japMember.oauthId,
            )
        }

        fun toDomainOAuthProvider(japOAuthProvider: JpaOAuthProvider): OAuthProvider {
            return OAuthProvider.valueOf(japOAuthProvider.name)
        }

        fun toJpaMember(member: Member): JpaMember {
            return JpaMember(
                id = member.id,
                oauthProvider = toJpaOAuthProvider(member.oauthProvider),
                oauthId = member.oauthId,
            )
        }

        fun toJpaOAuthProvider(oauthProvider: OAuthProvider): JpaOAuthProvider {
            return JpaOAuthProvider.valueOf(oauthProvider.name);
        }
    }
}
