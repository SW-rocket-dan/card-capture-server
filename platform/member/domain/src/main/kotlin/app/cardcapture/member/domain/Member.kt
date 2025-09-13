package app.cardcapture.member.domain

public class Member(
    public val id: Long,
    public val oauthProvider: OAuthProvider,
    public val oauthId: String,
) {
}
