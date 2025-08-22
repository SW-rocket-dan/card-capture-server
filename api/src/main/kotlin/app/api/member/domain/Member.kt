package app.api.member.domain

class Member (
    val id: Long = 0L,
    val provider: Provider,
    val oauthId : String,
) {
}
