package app.api.member.application.port.inbound

interface DevLoginUseCase {

    fun login(oauthId: String): String
}
