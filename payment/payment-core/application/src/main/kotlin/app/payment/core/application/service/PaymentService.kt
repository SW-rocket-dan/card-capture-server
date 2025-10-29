package app.payment.core.application.service

import app.payment.core.application.port.inbound.PaymentUseCase
import app.payment.core.application.port.outbound.PaymentReader
import app.payment.core.domain.Payment
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PaymentService(
    private val paymentReader: PaymentReader
) : PaymentUseCase {

    override fun getPayment(paymentId: Long): Payment {
        return paymentReader.findById(paymentId)
            ?: throw NoSuchElementException("Payment not found with id: $paymentId")
    }
}
