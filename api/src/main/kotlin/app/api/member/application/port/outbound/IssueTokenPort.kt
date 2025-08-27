package app.api.member.application.port.outbound

import app.api.member.domain.Member

interface IssueTokenPort {

    fun issue(member: Member): String

}
