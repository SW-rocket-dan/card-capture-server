plugins {
    id("io.spring.dependency-management")
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":security"))
    implementation(project(":auth-adapter"))
    implementation(project(":member-adapter"))


    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test")


    runtimeOnly("com.mysql:mysql-connector-j")

}
