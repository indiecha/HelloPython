plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.chaquo.python")
}


android {
    namespace = "com.example.unitymodule"
    compileSdk = 34

    defaultConfig {
//        applicationId = "com.example.unitymodule"
        minSdk = 24
        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            // On Apple silicon, you can omit x86_64.
//            abiFilters += listOf("arm64-v8a")
            abiFilters += listOf("armeabi-v7a", "x86", "x86_64")
//            abiFilters += listOf("arm64-v8a", "armeabi-v7a")

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

    chaquopy {
        defaultConfig {
            pip {
                // A requirement specifier, with or without a version number:
                install("scipy")
                install("matplotlib")
//                install("requests==2.24.0")

//                // An sdist or wheel filename, relative to the project directory:
//                install("MyPackage-1.2.3-py2.py3-none-any.whl")
//
//                // A directory containing a setup.py, relative to the project
//                // directory (must contain at least one slash):
//                install("./MyPackage")
//
//                // "-r"` followed by a requirements filename, relative to the
//                // project directory:
//                install("-r", "requirements.txt")
            }
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

}