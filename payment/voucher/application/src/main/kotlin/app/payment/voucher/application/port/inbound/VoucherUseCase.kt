package app.payment.voucher.application.port.inbound

interface VoucherUseCase {
    fun getPublishedVouchers(): List<CurrentPublishedVouchers>
}
