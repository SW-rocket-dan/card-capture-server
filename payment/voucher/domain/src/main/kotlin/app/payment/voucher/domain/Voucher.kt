package app.payment.voucher.domain

import java.time.OffsetDateTime

class Voucher(
    val id: Long,
    val type: VoucherType,
    val consumptionType: ConsumptionType,
    var status: VoucherStatus,
    val createdAt: OffsetDateTime,
    var updatedAt: OffsetDateTime,
)
