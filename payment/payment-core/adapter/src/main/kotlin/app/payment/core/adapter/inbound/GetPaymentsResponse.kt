package app.payment.core.adapter.inbound

import app.payment.core.domain.Payment

data class GetPaymentsResponse(
    val payments: List<GetPaymentResponse>,
    val count: Int
) {
    companion object {
        fun from(payments: List<Payment>): GetPaymentsResponse {
            return GetPaymentsResponse(
                payments = payments.map { GetPaymentResponse.from(it) },
                count = payments.size
            )
        }
    }
}