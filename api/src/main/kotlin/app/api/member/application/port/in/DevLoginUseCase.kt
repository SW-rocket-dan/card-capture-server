package app.api.member.application.port.`in`

interface DevLoginUseCase {

    fun login(oauthId: String): String
}
