package app.cardcapture.member.domain

class Member (
    val id: Long,
    val oauthProvider: OAuthProvider,
    val oauthId : String,
) {
}
