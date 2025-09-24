package app.payment.voucher.adapter.outbound

import app.payment.voucher.domain.VoucherStatus
import org.springframework.data.jpa.repository.JpaRepository

interface VoucherJpaRepository : JpaRepository<VoucherJpaEntity, Long> {
    fun findByStatus(status: VoucherStatus): List<VoucherJpaEntity>
}
