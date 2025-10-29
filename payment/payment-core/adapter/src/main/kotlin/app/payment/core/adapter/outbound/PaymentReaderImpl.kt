package app.payment.core.adapter.outbound

import app.payment.core.application.port.outbound.PaymentReader
import app.payment.core.domain.Payment
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PaymentReaderImpl(
    private val paymentJpaRepository: PaymentJpaRepository
) : PaymentReader {

    override fun findById(id: Long): Payment? {
        return paymentJpaRepository.findByIdOrNull(id)?.toDomain()
    }
}
