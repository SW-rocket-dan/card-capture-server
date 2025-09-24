package app.payment.voucher.domain

import java.time.OffsetDateTime

class VoucherContent(
    public val id: Long,
    public val voucherId: Long,
    public val version: Int,
    public val title: String,
    public val description: String,
    public val activeFrom: OffsetDateTime,
    public val activeUntil: OffsetDateTime,
    public val createdAt: OffsetDateTime,
)
