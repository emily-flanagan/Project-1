plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}
// got this from google - was having an issue :
// Duplicate class com.google.common.util.concurrent.ListenableFuture found in modules guava-23.0.jar -> guava-23.0 (com.google.guava:guava:23.0) and listenablefuture-1.0.jar -> listenablefuture-1.0 (com.google.guava:listenablefuture:1.0) 
configurations {
    all {
        exclude(group = "com.google.guava", module = "listenablefuture")
    }
}
android {
    namespace = "com.example.project1"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.project1"
        minSdk = 33
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // https://www.youtube.com/watch?v=4gUeyNkGE3g
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha04")
    implementation("com.google.maps.android:maps-compose:6.1.0")
    //Yelp Manager implementation
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    // coil dependency for image loading
    implementation("io.coil-kt:coil-compose:2.7.0")
}
