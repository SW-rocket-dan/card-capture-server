package app.payment.core.domain

import java.math.BigDecimal
import java.time.OffsetDateTime

class Payment(
    val id: Long,
    val orderId: String,
    val userId: Long,
    val amount: BigDecimal,
    val method: PaymentMethod,
    val pgProvider: PgProvider,
    val status: PaymentStatus,
    val transactionId: String?,
    val pgTransactionId: String?,
    val paidAt: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
) {
}
