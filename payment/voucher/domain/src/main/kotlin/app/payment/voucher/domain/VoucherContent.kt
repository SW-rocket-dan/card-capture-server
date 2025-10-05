package app.payment.voucher.domain

import java.time.OffsetDateTime

class VoucherContent(
    val id: Long,
    val voucherId: Long,
    val version: Int,
    val title: String,
    val description: String,
    val activeFrom: OffsetDateTime,
    val activeUntil: OffsetDateTime,
    val createdAt: OffsetDateTime,
)
