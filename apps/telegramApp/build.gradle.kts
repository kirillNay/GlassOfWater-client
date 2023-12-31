plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "nay.kirill"
version = "1.0-SNAPSHOT"

kotlin {
    setupPlatforms(
        platforms = listOf(Platform.JS),
        commonDeps = {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            implementation(project(":features:waterCounter"))
            implementation(project(":features:settings"))
            implementation(project(":domain"))
            implementation(project(":data"))
            implementation(project(":core:res"))
            implementation(project(":core:arch"))

            implementation("io.insert-koin:koin-core:3.2.0")
        },
        jsDeps = {
            implementation("com.kirillNay.telegram:mini-app:0.0.3")
        }
    )
}

compose.experimental {
    web.application {}
}

compose {
    val composeVersion = project.property("compose.wasm.version") as String
    kotlinCompilerPlugin.set(composeVersion)
    val kotlinVersion = project.property("kotlin.version") as String
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=$kotlinVersion")
}
