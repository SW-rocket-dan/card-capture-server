package app.payment.voucher.application.service

import app.payment.voucher.application.port.inbound.CurrentPublishedVouchers
import app.payment.voucher.application.port.inbound.VoucherUseCase
import app.payment.voucher.application.port.outbound.VoucherContentReader
import app.payment.voucher.application.port.outbound.VoucherReader
import app.payment.voucher.domain.VoucherStatus
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class VoucherService(
    private val voucherReader: VoucherReader,
    private val voucherContentReader: VoucherContentReader,
) : VoucherUseCase {
    override fun getPublishedVouchers(): List<CurrentPublishedVouchers> {
        val publishedVouchers = voucherReader.readByStatus(VoucherStatus.PUBLISHED)
        if (publishedVouchers.isEmpty()) {
            return emptyList()
        }

        val publishedVoucherIds = publishedVouchers.map { it.id }
        val now = OffsetDateTime.now()
        val currentVoucherContents = voucherContentReader.readCurrentContents(publishedVoucherIds, now)
        val contentsByVoucherId = currentVoucherContents.associateBy { it.voucherId }

        return publishedVouchers.mapNotNull { voucher ->
            val voucherContent = contentsByVoucherId[voucher.id] ?: return@mapNotNull null

            CurrentPublishedVouchers(
                id = voucher.id,
                type = voucher.type,
                consumptionType = voucher.consumptionType,
                title = voucherContent.title,
                description = voucherContent.description,
                activeUntil = voucherContent.activeUntil,
            )
        }
    }
}
