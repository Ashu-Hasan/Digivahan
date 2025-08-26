plugins {
    alias(libs.plugins.android.application)
    id("com.onesignal.androidsdk.onesignal-gradle-plugin") version "0.14.0"
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.ash.digivahan"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ash.digivahan"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
//        multiDexEnabled = true
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
        debug {
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

//    implementation(libs.lifecycle.livedata.ktx)
//    implementation(libs.lifecycle.viewmodel.ktx)
//    implementation(libs.navigation.fragment)
//    implementation(libs.navigation.ui)

   /* // ⚡ Firebase Crashlytics & Analytics (runtime)
    dependencies {
        // Import the Firebase BoM
        implementation(platform("com.google.firebase:firebase-bom:33.5.1"))

        // Add Firebase dependencies without versions
        implementation("com.google.firebase:firebase-crashlytics")
        implementation("com.google.firebase:firebase-analytics")
    }*/


    /*implementation(libs.volley)
    implementation(libs.okhttp3)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)

    // to load images
    implementation(libs.picasso)
    implementation(libs.flexbox)
    implementation(libs.sdp)
    implementation(libs.ssp)
    implementation(libs.wdullaer)
    implementation(libs.easyupipayment)

    // Razorpay
    implementation(libs.checkout)

    // OneSignal SDK
    implementation(libs.onesignal)

    // Multidex
    implementation(libs.multidex)
    implementation(libs.play.services.location)
    implementation(libs.play.services.auth)

    // Facebook SDK
    implementation(libs.facebook.android.sdk)

    // Lottie
    implementation(libs.lottie)*/

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Lifecycle & ViewModel
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.4")
    implementation("androidx.lifecycle:lifecycle-livedata:2.8.4")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.11")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    annotationProcessor("com.google.dagger:hilt-compiler:2.51.1")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

}
