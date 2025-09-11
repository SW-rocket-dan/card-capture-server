package app.cardcapture.auth.domain

class AuthMember (
    val id: Long,
    val oauthId: String,
    val oauthProvider: OAuthProvider,
){
}
