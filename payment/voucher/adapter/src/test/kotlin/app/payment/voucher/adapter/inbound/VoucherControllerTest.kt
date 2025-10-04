package app.payment.voucher.adapter.inbound

import app.payment.voucher.application.port.inbound.CurrentPublishedVouchers
import app.payment.voucher.application.port.inbound.VoucherUseCase
import app.payment.voucher.domain.ConsumptionType
import app.payment.voucher.domain.VoucherType
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.OffsetDateTime

class VoucherControllerTest {

    private lateinit var mockMvc: MockMvc
    private lateinit var voucherUseCase: VoucherUseCase

    @BeforeEach
    fun setUp() {
        voucherUseCase = mockk()
        mockMvc = MockMvcBuilders.standaloneSetup(VoucherController(voucherUseCase)).build()
    }

    @Test
    @DisplayName("GET /api/v1/vouchers 요청 시 발행된 바우처 목록을 반환한다")
    fun `should return published vouchers`() {
        // given
        val vouchers = listOf(
            CurrentPublishedVouchers(
                id = 1L,
                type = VoucherType.AI_POSTER_GENERATE,
                consumptionType = ConsumptionType.SINGLE_USE,
                title = "AI 포스터 생성",
                description = "AI를 사용한 포스터 생성 바우처",
                activeUntil = OffsetDateTime.now().plusDays(30)
            )
        )

        every { voucherUseCase.getPublishedVouchers() } returns vouchers

        // when & then
        mockMvc.perform(get("/api/v1/vouchers"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.vouchers[0].id").value(1))
            .andExpect(jsonPath("$.vouchers[0].title").value("AI 포스터 생성"))
            .andExpect(jsonPath("$.vouchers[0].consumptionType").value("SINGLE_USE"))
            .andExpect(jsonPath("$.vouchers[0].description").value("AI를 사용한 포스터 생성 바우처"))
    }

    @Test
    @DisplayName("발행된 바우처가 없으면 빈 목록을 반환한다")
    fun `should return empty list when no published vouchers`() {
        // given
        every { voucherUseCase.getPublishedVouchers() } returns emptyList()

        // when & then
        mockMvc.perform(get("/api/v1/vouchers"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.vouchers").isEmpty())
    }
}
