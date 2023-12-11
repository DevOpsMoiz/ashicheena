plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.languagetranslater"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.languagetranslater"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("com.google.firebase:firebase-ml-vision:24.1.0")
    implementation ("com.google.firebase:firebase-core:21.1.1")
    implementation ("com.google.mlkit:translate:17.0.2")

    implementation("com.google.firebase:firebase-ml-natural-language:22.0.0")
    implementation("com.google.firebase:firebase.ml.natural-language-id-model:20.0.7")
    implementation("com.google.firebase:firebase.ml.natural-language-translate-id-model:20.0.8")
    implementation("org.jetbrains:annotations:15.0")
    implementation("org.jetbrains:annotations:15.0")
    implementation("org.jetbrains:annotations:15.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}