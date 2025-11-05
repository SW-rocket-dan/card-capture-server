package app.cardcapture.template.adapter.inbound.web.dto

import domain.Template


data class GenerateCardTemplateResponse(
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String,
    val likes: Int, // TODO
    val purchaseCount: Int, // TODO
    val editor: String,
    val fileUrl: String?,
    val templateTags: List<TemplateTagsResponse>,

    ) {

    data class TemplateTagsResponse(
        val english: String,
        val korean: String,
    )

    companion object {

        fun from(template: Template): GenerateCardTemplateResponse {
            return GenerateCardTemplateResponse(
                id = template.id,
                userId = template.userId,
                title = template.title,
                description = template.description,
                likes = 0,
                purchaseCount = 0,
                editor = template.editorPayload.editor,
                fileUrl = template.fileUrl,
                templateTags = listOf(),
            )
        }
    }
}
