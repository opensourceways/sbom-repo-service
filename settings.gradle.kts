rootProject.name = "sbom-repo-service"

pluginManagement {
    plugins {
        id("org.springframework.boot") version "2.7.0"
        id("io.spring.dependency-management") version "1.0.11.RELEASE"
    }

    repositories {
        // mavenCentral()
        maven {
            url = uri("https://mirrors.huaweicloud.com/repository/maven/")
        }
    }
}
