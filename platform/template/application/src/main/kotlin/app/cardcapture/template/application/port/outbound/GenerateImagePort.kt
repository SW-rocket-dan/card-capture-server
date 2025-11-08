package app.cardcapture.template.application.port.outbound

import app.cardcapture.template.application.port.outbound.dto.GenerateImagePortCommand

interface GenerateImagePort {
    fun generate(command: GenerateImagePortCommand): String
}
