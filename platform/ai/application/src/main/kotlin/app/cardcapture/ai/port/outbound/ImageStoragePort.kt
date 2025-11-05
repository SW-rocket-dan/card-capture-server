package app.cardcapture.ai.port.outbound

interface ImageStoragePort {

    fun upload(bytes: ByteArray, key: String, fileName: String): String
}
