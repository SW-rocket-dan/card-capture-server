package app.payment.voucher.adapter.outbound

import app.payment.voucher.application.port.outbound.VoucherReader
import app.payment.voucher.domain.Voucher
import app.payment.voucher.domain.VoucherStatus
import org.springframework.stereotype.Repository

@Repository
class VoucherReaderImpl(
    private val voucherRepository: VoucherJpaRepository
): VoucherReader {
    override fun readByStatus(status: VoucherStatus): List<Voucher> {
        return voucherRepository.findByStatus(status)
            .map { it.toDomain() }
    }
}
