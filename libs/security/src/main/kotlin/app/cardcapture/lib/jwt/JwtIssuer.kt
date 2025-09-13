package app.cardcapture.lib.jwt

interface JwtIssuer {

    fun issue(claims: Map<String, Any>): String
}
