package app.payment.voucher.application.port.outbound

import app.payment.voucher.domain.Voucher
import app.payment.voucher.domain.VoucherStatus

interface VoucherReader {
    fun readByStatus(status: VoucherStatus): List<Voucher>
}
