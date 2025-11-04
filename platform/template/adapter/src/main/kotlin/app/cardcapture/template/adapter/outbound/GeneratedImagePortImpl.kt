package app.cardcapture.template.adapter.outbound

import app.cardcapture.template.application.port.outbound.GenerateImagePort
import app.cardcapture.template.application.port.outbound.dto.GenerateImagePortCommand
import org.springframework.stereotype.Component

@Component
class GeneratedImagePortImpl (

): GenerateImagePort{

    override fun generate(command: GenerateImagePortCommand): String {

        return "isUrl---!"
    }
}
