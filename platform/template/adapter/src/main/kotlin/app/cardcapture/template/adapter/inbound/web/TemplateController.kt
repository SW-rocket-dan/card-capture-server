package app.cardcapture.template.adapter.inbound.web

import app.cardcapture.template.adapter.inbound.web.dto.GenerateCardTemplateRequest
import app.cardcapture.template.adapter.inbound.web.dto.GenerateCardTemplateResponse
import app.cardcapture.template.application.port.inbound.GenerateCardTemplateUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController(
    private val generateCardTemplateUseCase: GenerateCardTemplateUseCase
) {

    @PostMapping("/api/template")
    fun generate(@RequestBody request: GenerateCardTemplateRequest): ResponseEntity<GenerateCardTemplateResponse> {

        val result = generateCardTemplateUseCase.generate(request.toCommand())

        return ResponseEntity.ok<GenerateCardTemplateResponse>(GenerateCardTemplateResponse.from(result))
    }
}
