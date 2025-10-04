package app.payment.voucher.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import java.time.OffsetDateTime

class VoucherTest {

    @Test
    @DisplayName("바우처 상태를 변경할 수 있다")
    fun `should change voucher status`() {
        // given
        val voucher = createVoucher()

        // when
        voucher.status = VoucherStatus.PAUSED

        // then
        assertThat(voucher.status).isEqualTo(VoucherStatus.PAUSED)
    }

    @Test
    @DisplayName("바우처가 활성 상태인지 확인할 수 있다")
    fun `should check if voucher is active`() {
        // given
        val activeVoucher = createVoucher(status = VoucherStatus.PUBLISHED)
        val pausedVoucher = createVoucher(status = VoucherStatus.PAUSED)

        // then
        assertThat(activeVoucher.status).isEqualTo(VoucherStatus.PUBLISHED)
        assertThat(pausedVoucher.status).isNotEqualTo(VoucherStatus.PUBLISHED)
    }

    private fun createVoucher(
        status: VoucherStatus = VoucherStatus.DRAFT
    ) = Voucher(
        id = 1L,
        type = VoucherType.AI_POSTER_GENERATE,
        consumptionType = ConsumptionType.SINGLE_USE,
        status = status,
        createdAt = OffsetDateTime.now(),
        updatedAt = OffsetDateTime.now()
    )
}