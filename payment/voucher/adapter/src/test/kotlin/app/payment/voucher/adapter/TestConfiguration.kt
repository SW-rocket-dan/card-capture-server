package app.payment.voucher.adapter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("app.payment.voucher.adapter.outbound")
@EnableJpaRepositories("app.payment.voucher.adapter.outbound")
class TestConfiguration