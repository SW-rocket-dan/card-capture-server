package app.cardcapture.api.platform

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


// TODO : 패키지 경로 루트로 변경하면 아래 코드 사라짐 논의 필요
@SpringBootApplication (
    scanBasePackages = ["app.cardcapture"],
)
@ConfigurationPropertiesScan(
    basePackages = ["app.cardcapture"],
)
@EntityScan(basePackages = ["app.cardcapture"])
@EnableJpaRepositories(
    basePackages = [
        "app.cardcapture"
    ]
)
class PlatformApiApplication

fun main(args: Array<String>) {
    runApplication<PlatformApiApplication>(*args)
}
