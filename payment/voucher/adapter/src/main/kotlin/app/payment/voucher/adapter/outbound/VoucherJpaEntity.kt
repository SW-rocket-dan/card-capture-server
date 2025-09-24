package app.payment.voucher.adapter.outbound

import app.payment.voucher.domain.ConsumptionType
import app.payment.voucher.domain.Voucher
import app.payment.voucher.domain.VoucherStatus
import app.payment.voucher.domain.VoucherType
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@Entity
@Table(name = "vouchers")
@EntityListeners(AuditingEntityListener::class)
class VoucherJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Enumerated(EnumType.STRING)
    val type: VoucherType,
    @Enumerated(EnumType.STRING)
    val consumptionType: ConsumptionType,
    @Enumerated(EnumType.STRING)
    var status: VoucherStatus,
    @CreatedDate
    var createdAt: OffsetDateTime? = null,
    @LastModifiedDate
    var updatedAt: OffsetDateTime? = null
) {
    fun toDomain(): Voucher {
        val id = requireNotNull(id) { "Voucher.id must not be null" }
        val createdAt = requireNotNull(createdAt) { "Voucher.createdAt must not be null" }
        val updatedAt = requireNotNull(updatedAt) { "Voucher.updatedAt must not be null" }

        return Voucher(
            id = id,
            type = type,
            consumptionType = consumptionType,
            status = status,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
