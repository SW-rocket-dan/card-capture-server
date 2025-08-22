package app.api.member.adapter.out.jwt

import app.api.member.application.port.out.IssueTokenPort
import app.api.member.domain.Member
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class JwtTokenAdapter : IssueTokenPort {

    override fun issue(member: Member): String {
        // TODO : Jwt 로직 추가
        return UUID.randomUUID().toString()
    }


}
