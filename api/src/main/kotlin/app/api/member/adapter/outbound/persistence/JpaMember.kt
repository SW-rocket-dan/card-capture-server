package app.api.member.adapter.outbound.persistence

import jakarta.persistence.*

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



}
