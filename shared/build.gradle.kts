import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.google.devtools.ksp") version "2.0.21-1.0.26"
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    js(IR){
        browser {
            testTask{
                useKarma {
                    useChromeHeadless()
                    useChrome()
                }
            }
        }
        nodejs{
            testTask{
                useMocha {  }
            }
        }
        binaries.library()
        useCommonJs()
        generateTypeScriptDefinitions()
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }

        jsMain.dependencies {
            implementation(kotlin("stdlib-js"))
        }
        commonMain.dependencies {
            //put your multiplatform dependencies here
            api(libs.kotlinx.coroutine)
            api(libs.koin.core)
            api(libs.ionspin.kotlin.bignum)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutine.test)
            implementation(libs.koin.test)
        }
    }
}

dependencies {
    configurations
        .filter {
            it.name.startsWith("ksp") && it.name.contains("Test")
        }
        .forEach {
            add(it.name, "io.mockative:mockative-processor:2.2.2")
        }
}


android {
    namespace = "com.example.bmicalculator"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
