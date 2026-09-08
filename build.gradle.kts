import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktor)
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(17)
}

dependencies {


    // Ktor Server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.resources)
    implementation(libs.ktor.server.request.validation)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Other dependencies
    implementation(libs.koin.ktor)
    implementation(libs.mongo.driver.kotlin)
    implementation(libs.koin.loggerSlf4j)
    implementation(libs.logback.classic)

    // Testing
    testImplementation(kotlin("test"))
    testImplementation(libs.ktor.server.test.host)
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions {
    freeCompilerArgs.addAll("-Xannotation-default-target=param-property")
}