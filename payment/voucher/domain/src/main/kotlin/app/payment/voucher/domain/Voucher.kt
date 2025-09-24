package app.payment.voucher.domain

import java.time.OffsetDateTime

class Voucher(
    public val id: Long,
    public val type: VoucherType,
    public val consumptionType: ConsumptionType,
    public var status: VoucherStatus,
    public val createdAt: OffsetDateTime,
    public var updatedAt: OffsetDateTime,
)
