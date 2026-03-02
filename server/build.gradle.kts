plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.er.legbon"
version = "1.0.0"

application {
    mainClass.set("com.er.legbon.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    // Project Modules
    implementation(projects.shared)

    // Ktor Server
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)

    // Exposed ORM
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.datetime)

    // Database & Connection Pooling
    implementation(libs.hikaricp)
    implementation(libs.postgresql)

    // Utilities
    implementation(libs.jbcrypt)
    implementation(libs.logback)

    // Testing
    testImplementation(libs.kotlin.testJunit)
    testImplementation(libs.ktor.server.test.host)
}
