package app.api.member.adapter.inbound.web.dto

class LoginResponse(
    val accessToken: String
) {

    companion object {
        fun from(accessToken: String): LoginResponse = LoginResponse(accessToken)
    }
}
