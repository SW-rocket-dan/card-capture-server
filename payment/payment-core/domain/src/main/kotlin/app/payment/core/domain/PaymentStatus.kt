package app.payment.core.domain

enum class PaymentStatus {
    READY,
    PAY_PENDING,
    PAID,
    PARTIAL_CANCELLED,
    CANCELLED,
    FAILED,
}
