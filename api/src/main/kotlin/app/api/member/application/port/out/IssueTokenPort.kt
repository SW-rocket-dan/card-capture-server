package app.api.member.application.port.out

import app.api.member.domain.Member

interface IssueTokenPort {

    fun issue(member: Member): String

}
