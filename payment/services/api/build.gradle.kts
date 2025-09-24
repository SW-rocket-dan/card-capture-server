plugins {
    id("io.spring.dependency-management")
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":voucher-adapter"))


    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.h2database:h2")
    testImplementation("io.rest-assured:rest-assured")

    // 테스트에서 도메인 클래스 접근을 위해 필요
    testImplementation(project(":voucher-domain"))
    testImplementation(project(":voucher-application"))


    runtimeOnly("com.mysql:mysql-connector-j")

}

tasks.withType<Test> {
    useJUnitPlatform()
}