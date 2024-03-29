plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.tv.instamenu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tv.instamenu"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //UI
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")

//Glide for Image loading and image processing
    implementation ("com.github.bumptech.glide:glide:4.8.0")
    kapt ("com.github.bumptech.glide:compiler:4.8.0")


    // dependency for exoplayer
    implementation ("com.google.android.exoplayer:exoplayer:2.19.1")

// for core support in exoplayer.
    implementation ("com.google.android.exoplayer:exoplayer-core:2.19.1")


// for smooth streaming of video in our exoplayer.
    implementation ("com.google.android.exoplayer:exoplayer-smoothstreaming:2.19.1")

// for generating default ui of exoplayer
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.19.1")


    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")


    // Lifecycle and Viewmodal
    implementation ("android.arch.lifecycle:extensions:1.1.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // Architectural Components
    implementation ("androidx.activity:activity-ktx:1.7.2")
    implementation ("androidx.fragment:fragment-ktx:1.6.1")

    // Hilt DI
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")


    // implementation ("com.github.Koitharu:pausing-coroutine-dispatcher:1.0")

    //implementation ("com.google.android.exoplayer:exoplayer:2.19.1")


}