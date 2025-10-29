package app.payment.core.adapter.outbound

import app.payment.core.domain.Payment
import app.payment.core.domain.PaymentMethod
import app.payment.core.domain.PaymentStatus
import app.payment.core.domain.PgProvider
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "payments")
class PaymentJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "order_id", nullable = false, unique = true)
    val orderId: String,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(nullable = false, precision = 19, scale = 2)
    val amount: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val method: PaymentMethod,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val pgProvider: PgProvider,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: PaymentStatus,

    @Column(name = "transaction_id")
    val transactionId: String? = null,

    @Column(name = "pg_transaction_id")
    val pgTransactionId: String? = null,

    @Column(name = "paid_at")
    val paidAt: OffsetDateTime? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    val updatedAt: OffsetDateTime = OffsetDateTime.now()
) {
    fun toDomain(): Payment {
        return Payment(
            id = id,
            orderId = orderId,
            userId = userId,
            amount = amount,
            method = method,
            pgProvider = pgProvider,
            status = status,
            transactionId = transactionId,
            pgTransactionId = pgTransactionId,
            paidAt = paidAt,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
