package app.cardcapture.auth.application.port.inbound

interface DevelopmentLoginUseCase {

    fun login(oauthId: String): String
}
