plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.foodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodapp"
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
}

dependencies {
    // Sử dụng Firebase BoM để quản lý các phiên bản của Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))

    // Các thư viện Firebase sẽ tự động dùng phiên bản từ BoM
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")


    //Payment
    implementation("com.stripe:stripe-android:20.52.0")

    implementation ("com.github.bumptech.glide:glide:4.15.0")
    implementation(libs.translate)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.0")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("com.squareup.picasso:picasso:2.71828")

    //Payment
    implementation("com.stripe:stripe-android:20.52.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    implementation("com.google.ai.client.generativeai:generativeai:0.1.2")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.sun.mail:android-mail:1.6.2")
    implementation("com.sun.mail:android-activation:1.6.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

