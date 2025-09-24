package app.payment.voucher.adapter.inbound

data class GetVoucherResponse(
    val id: Long,
    val consumptionType: String,
    val title: String,
    val description: String,
)
