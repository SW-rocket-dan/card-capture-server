package app.cardcapture.template.application.port.outbound

import domain.TemplateDesignPrompt

interface  TemplateDesignPort {

    fun design(prompt: TemplateDesignPrompt): String
}
