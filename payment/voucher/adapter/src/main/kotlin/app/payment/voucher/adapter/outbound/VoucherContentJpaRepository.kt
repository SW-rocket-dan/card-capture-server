package app.payment.voucher.adapter.outbound

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.OffsetDateTime

interface VoucherContentJpaRepository : JpaRepository<VoucherContentJpaEntity, Long> {
    @Query("""
        SELECT vc FROM VoucherContentJpaEntity vc
        WHERE vc.voucherId IN :voucherIds
        AND vc.activeFrom <= :now
        AND vc.activeUntil >= :now
    """)
    fun findActiveContentsByVoucherIds(
        @Param("voucherIds") voucherIds: List<Long>,
        @Param("now") now: OffsetDateTime
    ): List<VoucherContentJpaEntity>
}
