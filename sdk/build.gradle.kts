java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

plugins {
    id("com.android.library")
//    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("kotlin-parcelize")
    kotlin("plugin.serialization")
    `maven-publish`
}


android {
    namespace = "com.iamport.sdk"

    compileSdk = 35

    defaultConfig {
        minSdk = 21
        multiDexEnabled = true
        targetSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
//        proguardFiles("proguard-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
    }

    lint {
        abortOnError = false
    }

    sourceSets {
        named("main") {
            java.srcDir("src/main/kotlin")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from(components["release"])
                groupId = "com.github.iamport"
                artifactId = "iamport-android"
                version = Versions.versionName
            }
        }

    }
}

dependencies {

    implementation(Libs.multidex)
    implementation(Libs.kotlin_stdlib_jdk)
    implementation(Libs.core_ktx)
    implementation(Libs.appcompat)

    testImplementation(Libs.robolectric)
    testImplementation(Libs.mokito_core)
    testImplementation(Libs.mokito_inline)
    testImplementation(Libs.mokito_android)

    testImplementation(Libs.junit)
    androidTestImplementation(Libs.testJunit)
    androidTestImplementation(Libs.espresso_core)

    testImplementation(Libs.androidx_test)

    // Android Support/Architecture
    implementation(Libs.constraintlayout)
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_livedata_ktx)
    implementation(Libs.lifecycle_common)
    implementation(Libs.material)

    implementation(Libs.activity_ktx)
    implementation(Libs.fragment_ktx)

    implementation(Libs.koin_core)
    testImplementation(Libs.koin_test)
    testImplementation(Libs.koin_test_junit4)
    implementation(Libs.koin_android)
    implementation(Libs.koin_android_compat)

    // Retorofit
    implementation(Libs.retrofit)
    implementation(Libs.converter_gson)
    implementation(Libs.logging_interceptor)

    // Gson
    implementation(Libs.gson)

    // Logger
    implementation(Libs.logger)

    // Rx
    implementation(Libs.rxjava)
    implementation(Libs.rxandroid)
    implementation(Libs.rxjava2_debug)

    implementation(Libs.serialization)
    implementation(Libs.workRuntimeKtx)

    implementation(Libs.lottie)
}
