package app.api.member.application.port.inbound

interface DevelopmentLoginUseCase {

    fun login(oauthId: String): String
}
