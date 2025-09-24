package app.payment.voucher.application.service

import app.payment.voucher.application.port.outbound.VoucherContentReader
import app.payment.voucher.application.port.outbound.VoucherReader
import app.payment.voucher.domain.*
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import java.time.OffsetDateTime

@ExtendWith(MockKExtension::class)
class VoucherServiceTest {

    @MockK
    private lateinit var voucherReader: VoucherReader

    @MockK
    private lateinit var voucherContentReader: VoucherContentReader

    @InjectMockKs
    private lateinit var voucherService: VoucherService

    @BeforeEach
    fun setUp() {
        clearMocks(voucherReader, voucherContentReader)
    }

    @Test
    @DisplayName("발행된 바우처 목록을 조회할 수 있다")
    fun `should get published vouchers`() {
        // given
        val vouchers = listOf(createVoucher(1L), createVoucher(2L))
        val contents = listOf(createVoucherContent(1L), createVoucherContent(2L))

        every { voucherReader.readByStatus(VoucherStatus.PUBLISHED) } returns vouchers
        every {
            voucherContentReader.readCurrentContents(listOf(1L, 2L), any())
        } returns contents

        // when
        val result = voucherService.getPublishedVouchers()

        // then
        assertEquals(2, result.size)
        assertEquals(1L, result[0].id)
        assertEquals("Test Title", result[0].title)

        verify(exactly = 1) { voucherReader.readByStatus(VoucherStatus.PUBLISHED) }
        verify(exactly = 1) { voucherContentReader.readCurrentContents(any(), any()) }
    }

    @Test
    @DisplayName("발행된 바우처가 없으면 빈 목록을 반환한다")
    fun `should return empty list when no published vouchers`() {
        // given
        every { voucherReader.readByStatus(VoucherStatus.PUBLISHED) } returns emptyList()

        // when
        val result = voucherService.getPublishedVouchers()

        // then
        assertTrue(result.isEmpty())

        verify(exactly = 1) { voucherReader.readByStatus(VoucherStatus.PUBLISHED) }
        verify(exactly = 0) { voucherContentReader.readCurrentContents(any(), any()) }
    }

    private fun createVoucher(id: Long) = Voucher(
        id = id,
        type = VoucherType.AI_POSTER_GENERATE,
        consumptionType = ConsumptionType.SINGLE_USE,
        status = VoucherStatus.PUBLISHED,
        createdAt = OffsetDateTime.now(),
        updatedAt = OffsetDateTime.now()
    )

    private fun createVoucherContent(voucherId: Long) = VoucherContent(
        id = voucherId * 10,
        voucherId = voucherId,
        version = 1,
        title = "Test Title",
        description = "Test Description",
        activeFrom = OffsetDateTime.now().minusDays(1),
        activeUntil = OffsetDateTime.now().plusDays(1),
        createdAt = OffsetDateTime.now()
    )
}
