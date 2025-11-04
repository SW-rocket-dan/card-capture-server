package app.cardcapture.template.application.service

import app.cardcapture.template.application.model.*
import org.springframework.stereotype.Component

@Component
class EditorJsonConverter {

    fun toJson(plan: AiTemplateDesignResult): String {
        val backgroundUrl =
            if (plan.background.mode == BackgroundModeDto.IMAGE)
                plan.background.url.orEmpty()
            else
                ""

        val bgColor =
            if (plan.background.mode == BackgroundModeDto.COLOR)
                plan.background.colorHex ?: "#FFFFFF"
            else
                plan.background.colorHex ?: "#FFFFFF"

        val layersJson = plan.layers.joinToString(",") { layer ->
            when (layer) {
                is PlannedTextLayerDto -> toTextLayerJson(layer)
                is PlannedImageLayerDto -> toImageLayerJson(layer)
            }
        }

        return """
        [
          {
            "id": 0,
            "background": {
              "url": "$backgroundUrl",
              "opacity": ${plan.background.opacity},
              "color": "$bgColor"
            },
            "layers": [ $layersJson ]
          }
        ]
        """.trimIndent()
    }

    private fun toTextLayerJson(layer: PlannedTextLayerDto): String {
        val text = escapeJson(layer.text)
        val font = escapeJson(layer.font)   // PlannedTextLayerDto에 font: String 존재한다고 가정
        val size = escapeJson(layer.size)   // size: String ("32px" 등)

        return """
        {
          "type": "text",
          "id": ${layer.id},
          "position": ${toPositionJson(layer.position)},
          "content": {
            "content": {
              "ops": [
                {
                  "insert": "$text",
                  "attributes": {
                    "font": "$font",
                    "size": "$size"
                  }
                },
                {
                  "insert": "\n"
                }
              ]
            }
          }
        }
        """.trimIndent()
    }

    private fun toImageLayerJson(layer: PlannedImageLayerDto): String {
        val url = layer.url.orEmpty()

        return """
        {
          "type": "image",
          "id": ${layer.id},
          "position": ${toPositionJson(layer.position)},
          "content": {
            "url": "$url"
          }
        }
        """.trimIndent()
    }

    private fun toPositionJson(pos: PositionDto): String =
        """{"x":${pos.x},"y":${pos.y},"width":${pos.width},"height":${pos.height},
           "rotate":${pos.rotate},"zIndex":${pos.zIndex},"opacity":${pos.opacity}}"""

    private fun escapeJson(text: String): String =
        text.replace("\"", "\\\"")
}
