package app.payment.core.application.port.outbound

import app.payment.core.domain.Payment

interface PaymentReader {
    fun findById(id: Long): Payment?
}
