plugins {
    kotlin("jvm") version "1.9.25" apply false
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.5.4" apply false
    kotlin("plugin.jpa") version "1.9.25" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
}

group = "app.cardcapture"
version = "0.0.1-SNAPSHOT"


allprojects {
    repositories { mavenCentral() }
}


subprojects {
    tasks.withType<Test> { useJUnitPlatform() }

    plugins.withId("java") {
        the<JavaPluginExtension>().toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        the<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension>().jvmToolchain(21)


        dependencies {
            add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher")
            add("testImplementation", "io.mockk:mockk:1.13.13")
        }
    }

    plugins.withId("io.spring.dependency-management") {

        the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.4")
            }
        }
    }


}

dependencies {
}
