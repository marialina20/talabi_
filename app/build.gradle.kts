plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.talabi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.talabi"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.compose.constraint.layout)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.tools.core)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.datastore.preferences.core.jvm)
    //implementation(libs.play.services.basement)
   // implementation(libs.google.play.services.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.core:core-ktx:1.13.1")
    implementation ("androidx.navigation:navigation-compose:2.8.3")
    //add by chatgpt in order to set up Node js here
    implementation ("io.ktor:ktor-client-core:2.0.0")
    implementation ("io.ktor:ktor-client-cio:2.0.0")
    implementation ("io.ktor:ktor-client-serialization:2.0.0")
    implementation ("io.ktor:ktor-client-json:2.0.0")
    //implementation ("com.google.android.gms:play-services-auth:15.0.0")
    implementation(libs.androidx.foundation)
    //noinspection UseTomlInstead
    implementation("androidx.compose.ui:ui:1.7.6")
    //implementation(libs.material3)
    implementation(libs.androidx.navigation)
    //implementation(libs.google.play.services.auth)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")
    implementation("com.google.android.gms:play-services-auth:20.7.0") {
        exclude(group = "com.android.support")
    }
    val room_version = "2.6.0"
    implementation (libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    testImplementation (libs.androidx.room.testing)
    implementation ("io.coil-kt:coil-compose:2.4.0")


}
