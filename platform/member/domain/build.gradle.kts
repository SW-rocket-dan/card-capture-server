plugins {
    kotlin("jvm")
    `java-library`
}

kotlin {
    jvmToolchain(21)
    explicitApi()
}

dependencies {
    // 순수 도메인
}
