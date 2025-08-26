package app.api.member.domain

class Member (
    val id: Long?,
    val oauthProvider: OAuthProvider,
    val oauthId : String,
) {
}
