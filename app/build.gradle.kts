plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "id.ac.polbeng.depandienda.kabkotaretrofitapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "id.ac.polbeng.depandienda.kabkotaretrofitapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Mengaktifkan viewBinding sesuai modul praktikum
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // Menambahkan dependencies library Retrofit, Glide, Logging Interceptor, dll.
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.glidetovectoryou)
    implementation(libs.touchimageview)

    // Fitur Swipe and Refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}