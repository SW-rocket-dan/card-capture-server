package app.payment.voucher.adapter.inbound

import app.payment.voucher.application.port.inbound.CurrentPublishedVouchers

data class GetVouchersResponse(
    val vouchers: List<GetVoucherResponse>,
) {
    companion object {
        fun from(items: List<CurrentPublishedVouchers>): GetVouchersResponse =
            GetVouchersResponse(
                vouchers =
                    items.map {
                        GetVoucherResponse(
                            id = it.id,
                            consumptionType = it.consumptionType.name,
                            title = it.title,
                            description = it.description,
                        )
                    },
            )
    }
}
