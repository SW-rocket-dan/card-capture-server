package app.cardcapture.ai.outbound.s3

import app.cardcapture.ai.port.outbound.ImageStoragePort
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.*


@Component
class ImageStorageAdapter(
    private val s3Client: S3Client,
    @Value("\${cloud.aws.s3.bucket}") private val bucket: String,
    @Value("\${cloud.aws.region.static}") private val region: String,
) : ImageStoragePort {


    override fun upload(bytes: ByteArray, key: String): String {
        val safePrefix = key.trimEnd('/')
        val objectKey = "$safePrefix/${UUID.randomUUID()}.png"


        val request = PutObjectRequest.builder()
            .bucket(bucket)
            .key(objectKey)
            .contentType("image/png")
            .build()

        s3Client.putObject(request, RequestBody.fromBytes(bytes))


        return "https://$bucket.s3.$region.amazonaws.com/$objectKey"
    }
}
