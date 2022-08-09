plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
    id("war")
}

group = "org.opensourceways.sbom.repo"
version = "1.0-SNAPSHOT"

repositories {
    // mavenCentral()
    maven {
        url = uri("https://mirrors.huaweicloud.com/repository/maven/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.apache.commons:commons-lang3")
    implementation("commons-io:commons-io:2.11.0")
    implementation("org.apache.logging.log4j:log4j-api")
    implementation("org.apache.logging.log4j:log4j-core")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl")
    implementation("org.slf4j:slf4j-api")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

springBoot {
    mainClass.set("org.opensourceways.sbom.repo.SbomRepoApplication")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}