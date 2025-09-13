package app.cardcapture.member.application.port.inbound.dto

data class AuthMemberView (
    val id: Long,
    val oauthId: String,
    val oauthProvider: String,
){
}
