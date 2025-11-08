package app.cardcapture.ai.port.outbound

interface ImageModelPort {

    fun generate(prompt: String, model: String) : ByteArray

}
