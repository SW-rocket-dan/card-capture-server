package app.payment.core.adapter.inbound

import app.payment.core.application.port.inbound.PaymentUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payments")
class PaymentController(
    private val paymentUseCase: PaymentUseCase
) {

    @GetMapping("/{id}")
    fun getPayment(@PathVariable id: Long): GetPaymentResponse {
        val payment = paymentUseCase.getPayment(id)
        return GetPaymentResponse.from(payment)
    }
}
