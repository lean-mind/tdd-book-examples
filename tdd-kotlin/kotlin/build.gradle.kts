import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("plugin.spring") version "1.5.31"
    id("com.diffplug.spotless") version "6.1.2"
    application
}

group = "es.leanmind"

version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17

repositories { mavenCentral() }

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.1.3")
    testImplementation("org.seleniumhq.selenium:selenium-support:4.1.3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> { useJUnitPlatform() }

spotless {
    kotlin { ktfmt() }
    kotlinGradle { ktfmt() }
}

tasks.check { dependsOn(tasks.spotlessCheck) }
