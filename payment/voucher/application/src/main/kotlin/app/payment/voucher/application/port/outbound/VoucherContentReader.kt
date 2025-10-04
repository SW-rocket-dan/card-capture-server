package app.payment.voucher.application.port.outbound

import app.payment.voucher.domain.VoucherContent
import java.time.OffsetDateTime

interface VoucherContentReader {
    fun readCurrentContents(
        publishedVoucherIds: List<Long>,
        now: OffsetDateTime,
    ): List<VoucherContent>
}
