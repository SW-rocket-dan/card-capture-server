package app.payment.voucher.adapter.inbound

import app.payment.voucher.application.port.inbound.VoucherUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/vouchers")
class VoucherController(
    private val voucherUseCase: VoucherUseCase,
) {
    @GetMapping
    fun getPublishedVouchers(): ResponseEntity<GetVouchersResponse> {
        val vouchers = voucherUseCase.getPublishedVouchers()
        return ResponseEntity.ok()
            .body(GetVouchersResponse.from(vouchers))
    }
}
