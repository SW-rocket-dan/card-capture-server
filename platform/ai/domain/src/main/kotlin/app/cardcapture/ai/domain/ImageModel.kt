package app.cardcapture.ai.domain

enum class ImageModel {
    NANOBANANA;

    companion object {
        fun from(name: String): ImageModel =
            ImageModel.entries.find { it.name.equals(name, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unsupported imageModel: $name")
    }
}
