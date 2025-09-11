package app.cardcapture.auth.application.port.outbound

import app.cardcapture.auth.domain.AuthMember


interface IssueTokenPort {

    fun issue(member: AuthMember): String

}
