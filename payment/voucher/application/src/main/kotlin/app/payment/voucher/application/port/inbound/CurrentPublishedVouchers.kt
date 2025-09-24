package app.payment.voucher.application.port.inbound

import app.payment.voucher.domain.ConsumptionType
import app.payment.voucher.domain.VoucherType
import java.time.OffsetDateTime

data class CurrentPublishedVouchers(
    val id: Long,
    val type: VoucherType,
    val consumptionType: ConsumptionType,
    val title: String,
    val description: String,
    val activeUntil: OffsetDateTime,
)
