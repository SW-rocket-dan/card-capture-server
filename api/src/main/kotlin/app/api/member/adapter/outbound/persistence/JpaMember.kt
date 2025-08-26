package app.api.member.adapter.outbound.persistence

import app.api.member.domain.Member
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "members")
class JpaMember(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var oauthProvider: JpaOAuthProvider,

    @Column(nullable = false)
    var oauthId: String


) {


    fun toDomain():Member {
        return Member(
            id = this.id,
            oauthId = this.oauthId,
            oauthProvider = this.oauthProvider.toDomain()
        )
    }


    companion object {

        fun fromDomain(member: Member): JpaMember {
            return JpaMember(
                id = member.id,
                oauthProvider = JpaOAuthProvider.fromDomain(member.oauthProvider),
                oauthId = member.oauthId,
            )
        }
    }
}
