package app.payment.core.application.port.inbound

import app.payment.core.domain.Payment

interface PaymentUseCase {
    fun getPayment(paymentId: Long): Payment
}
