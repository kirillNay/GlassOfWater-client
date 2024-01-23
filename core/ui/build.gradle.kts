plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    setupPlatforms(
        platforms = listOf(Platform.JS),
        commonDeps = {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material)
            api(compose.ui)

            implementation(project(":core:res"))
        }
    )
}

compose.experimental {
    web.application {}
}
