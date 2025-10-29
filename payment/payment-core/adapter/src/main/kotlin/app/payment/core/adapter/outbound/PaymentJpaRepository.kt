package app.payment.core.adapter.outbound

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PaymentJpaRepository : JpaRepository<PaymentJpaEntity, Long> {
}
