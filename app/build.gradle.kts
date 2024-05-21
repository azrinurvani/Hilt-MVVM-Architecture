plugins {
    //TODO - Step 3
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinAndroidKsp)
//    id("com.google.dagger.hilt.android")
//    id("kotlin-kapt")
}

android {
    namespace = "com.azrinurvani.latihanhiltmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.azrinurvani.latihanhiltmvvm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //TODO - Step 4
    //Android Material
    implementation(libs.material)
    //AppCompatActivity
    implementation(libs.androidx.appcompat)
    // For loading and tinting drawables on older versions of the platform
    implementation(libs.androidx.appcompat.resources)

    //ConstraintLayout
    implementation(libs.androidx.constraintlayout)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    //Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    testImplementation(libs.coroutines.test)

    //lifecycle
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.runtime)
    kapt(libs.lifecycle.compiler)  // If you need annotation processing

    //Activity and Fragment KTX
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    //Shimmer
    implementation(libs.shimmer)

    //Room
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    //Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Glide
    implementation(libs.glide)

    //MultiDex
    implementation(libs.androidx.multidex)

}
kapt {
    correctErrorTypes = true
//    verbose = true
}