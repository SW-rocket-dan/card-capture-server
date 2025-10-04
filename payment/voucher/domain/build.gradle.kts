plugins {
    kotlin("jvm")
    `java-library`
    id("io.spring.dependency-management")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // 순수 도메인

    // 테스트 - Spring Boot BOM 사용 (루트에서 자동 추가됨)
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
