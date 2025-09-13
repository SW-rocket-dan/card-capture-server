package app.cardcapture.auth.domain

public class AuthMember(
    public val id: Long,
    public val oauthId: String,
    public val oauthProvider: OAuthProvider,
) {
}
