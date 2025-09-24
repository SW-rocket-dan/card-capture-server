package app.payment.voucher.adapter.outbound

import app.payment.voucher.application.port.outbound.VoucherContentReader
import app.payment.voucher.domain.VoucherContent
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class VoucherContentReaderImpl(
    private val voucherContentRepository: VoucherContentJpaRepository,
) : VoucherContentReader {
    override fun readCurrentContents(publishedVoucherIds: List<Long>, now: OffsetDateTime): List<VoucherContent> {
        if (publishedVoucherIds.isEmpty()) {
            return emptyList()
        }

        return voucherContentRepository.findActiveContentsByVoucherIds(publishedVoucherIds, now)
            .map { it.toDomain() }
    }
}
