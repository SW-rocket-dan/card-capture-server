package app.payment.voucher.adapter.outbound

import app.payment.voucher.domain.ConsumptionType
import app.payment.voucher.domain.VoucherStatus
import app.payment.voucher.domain.VoucherType
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeEach
import java.time.OffsetDateTime

class VoucherReaderImplTest {

    private lateinit var voucherRepository: VoucherJpaRepository
    private lateinit var voucherReader: VoucherReaderImpl

    @BeforeEach
    fun setUp() {
        voucherRepository = mockk()
        voucherReader = VoucherReaderImpl(voucherRepository)
    }

    @Test
    @DisplayName("특정 상태의 바우처를 조회할 수 있다")
    fun `should find vouchers by status`() {
        // given
        val publishedEntity = VoucherJpaEntity(
            id = 1L,
            type = VoucherType.AI_POSTER_GENERATE,
            consumptionType = ConsumptionType.SINGLE_USE,
            status = VoucherStatus.PUBLISHED,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now()
        )

        every { voucherRepository.findByStatus(VoucherStatus.PUBLISHED) } returns listOf(publishedEntity)

        // when
        val result = voucherReader.readByStatus(VoucherStatus.PUBLISHED)

        // then
        assertEquals(1, result.size)
        assertEquals(VoucherStatus.PUBLISHED, result[0].status)
        assertEquals(1L, result[0].id)
    }

    @Test
    @DisplayName("해당 상태의 바우처가 없으면 빈 목록을 반환한다")
    fun `should return empty list when no vouchers with status`() {
        // given
        every { voucherRepository.findByStatus(VoucherStatus.PAUSED) } returns emptyList()

        // when
        val result = voucherReader.readByStatus(VoucherStatus.PAUSED)

        // then
        assertTrue(result.isEmpty())
    }
}