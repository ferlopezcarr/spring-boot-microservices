import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.12.0"
    id("org.liquibase.gradle") version "2.0.4"
    id("com.diffplug.spotless") version "7.0.2"
}

group = "com.ferlopezcarr.microservices"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // OpenAPI
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.6.0")
    implementation("jakarta.validation:jakarta.validation-api")

    // Authentication
    implementation("org.springframework.boot:spring-boot-starter-security")

    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.liquibase:liquibase-core")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.testcontainers:junit-jupiter")

    testImplementation("org.testcontainers:mongodb")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("-XX:+EnableDynamicAgentLoading")
}

val ktlintVersion = "1.5.0"
configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint(ktlintVersion)
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        ktlint(ktlintVersion)
    }
}

val oasKotlinSpringPackage = "$group.productService"
val oasAdapterInboundPackage = "$oasKotlinSpringPackage.infrastructure.inbound.rest"
val oasKotlinSpringOutputDir =
    layout.buildDirectory
        .dir("generated")
        .get()
        .asFile
        .toString()
val oasInputFile = "$projectDir/src/main/resources/openapi.spec.yaml"

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set(oasInputFile)
    outputDir.set(oasKotlinSpringOutputDir)
    apiPackage.set("$oasAdapterInboundPackage.api")
    modelPackage.set("$oasAdapterInboundPackage.model")
    modelNameSuffix.set("DTO")
    configOptions.set(
        mapOf(
            "useSpringBoot3" to "true",
            "delegatePattern" to "false",
            "interfaceOnly" to "true",
        ),
    )
}

openApiValidate {
    inputSpec.set(oasInputFile)
}

tasks.named("compileKotlin") {
    dependsOn(tasks.named("openApiGenerate"))
}

tasks.named("check") {
    dependsOn(tasks.named("openApiValidate"))
}

sourceSets {
    main {
        kotlin {
            srcDir {
                layout.buildDirectory
                    .dir("generated/src/main/kotlin")
                    .get()
                    .asFile
                    .toString()
            }
        }
    }
}
