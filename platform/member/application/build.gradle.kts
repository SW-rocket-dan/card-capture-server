plugins {
    kotlin("jvm")
    `java-library`
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(21)) }
}

dependencies {
    implementation(project(":member-domain"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.4"))
    implementation("org.springframework:spring-context")

}
