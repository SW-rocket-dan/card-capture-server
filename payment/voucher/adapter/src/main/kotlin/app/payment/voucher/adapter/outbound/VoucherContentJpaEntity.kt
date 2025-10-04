package app.payment.voucher.adapter.outbound

import app.payment.voucher.domain.VoucherContent
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@Entity
@Table(name = "voucher_contents")
@EntityListeners(AuditingEntityListener::class)
class VoucherContentJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val voucherId: Long,
    val version: Int,
    val title: String,
    val description: String,
    val activeFrom: OffsetDateTime,
    val activeUntil: OffsetDateTime,
    @CreatedDate
    var createdAt: OffsetDateTime? = null
) {
    fun toDomain(): VoucherContent {
        val id = requireNotNull(id) { "VoucherContent.id must not be null" }
        val createdAt = requireNotNull(createdAt) { "VoucherContent.createdAt must not be null" }

        return VoucherContent(
            id = id,
            voucherId = voucherId,
            version = version,
            title = title,
            description = description,
            activeFrom = activeFrom,
            activeUntil = activeUntil,
            createdAt = createdAt
        )
    }
}
