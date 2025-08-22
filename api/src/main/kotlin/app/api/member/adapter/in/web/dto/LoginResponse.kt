package app.api.member.adapter.`in`.web.dto

class LoginResponse(
    val accessToken: String
) {

    companion object {
        fun from(accessToken: String): LoginResponse = LoginResponse(accessToken)
    }
}
