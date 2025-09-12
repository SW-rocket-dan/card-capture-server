plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}


java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }
dependencies {
    // contracts-auth 구현
    implementation(project(":contracts-auth"))


    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-web")

}
