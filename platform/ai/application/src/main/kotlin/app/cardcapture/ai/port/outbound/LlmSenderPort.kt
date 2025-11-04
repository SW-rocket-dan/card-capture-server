package app.cardcapture.ai.port.outbound

import app.cardcapture.ai.domain.LlmRequest
import app.cardcapture.ai.domain.LlmResponse

interface LlmSenderPort {

    fun send(request: LlmRequest): LlmResponse
}
