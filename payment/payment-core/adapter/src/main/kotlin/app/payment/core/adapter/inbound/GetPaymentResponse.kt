package app.payment.core.adapter.inbound

import app.payment.core.domain.Payment
import app.payment.core.domain.PaymentMethod
import app.payment.core.domain.PaymentStatus
import java.math.BigDecimal
import java.time.OffsetDateTime

data class GetPaymentResponse(
    val id: Long,
    val orderId: String,
    val userId: Long,
    val amount: BigDecimal,
    val method: PaymentMethod,
    val status: PaymentStatus,
    val transactionId: String?,
    val paidAt: OffsetDateTime?,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
) {
    companion object {
        fun from(payment: Payment): GetPaymentResponse {
            return GetPaymentResponse(
                id = payment.id,
                orderId = payment.orderId,
                userId = payment.userId,
                amount = payment.amount,
                method = payment.method,
                status = payment.status,
                transactionId = payment.transactionId,
                paidAt = payment.paidAt,
                createdAt = payment.createdAt,
                updatedAt = payment.updatedAt
            )
        }
    }
}