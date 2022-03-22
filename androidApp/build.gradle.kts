plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation(Deps.AndroidX.recyclerView)
    implementation(Deps.AndroidX.swipeRefresh)
    implementation(Deps.material)
    implementation(Deps.AndroidX.appcompat)
    implementation(Deps.AndroidX.core_ktx)
    implementation(Deps.Ktor.androidCore)
    implementation(Deps.AndroidX.constraintlayout)
    implementation(Deps.Coroutines.common)
    implementation(Deps.Coroutines.android)
    implementation(Deps.AndroidX.lifecycle_runtime)
    implementation(Deps.AndroidX.lifecycle_viewmodel)
    implementation(Deps.AndroidX.lifecycle_viewmodel_extensions)
    implementation(Deps.AndroidX.lifecycle_livedata)
    implementation(Deps.AndroidX.lifecycle_extension)

    implementation(Compose.ui)
    implementation(Compose.uiGraphics)
    implementation(Compose.uiTooling)
    implementation(Compose.foundationLayout)
    implementation(Compose.foundation)

    implementation(Compose.material)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.navigation)
    implementation(Compose.accompanist)
    implementation(Koin.compose)

    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")

    androidTestImplementation ("androidx.test:runner:1.4.0")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.4.0")

    androidTestImplementation ("androidx.test:core:1.4.0")

    androidTestImplementation ("androidx.test.ext:junit:1.1.3")

    androidTestImplementation ("androidx.test:rules:1.4.0")
    androidTestImplementation ("org.mockito:mockito-core:4.3.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.0-alpha05")
    androidTestImplementation("androidx.compose.ui:ui-test:1.2.0-alpha05")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("com.schibsted.spain:barista:3.9.0")
}

android {
    packagingOptions {
        exclude ("META-INF/DEPENDENCIES.TXT")
        exclude ("META-INF/LICENSE.TXT")
        exclude ("META-INF/AL2.0")
        pickFirst ("META-INF/LGPL2.1")

    }
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "de.jensklingenberg.newmyapplication.androidApp"
        minSdkVersion(24)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.TestRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true

    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }
    kotlinOptions {
        jvmTarget = "11"
    }


    android {
        lintOptions {
            this.isAbortOnError= false
        }

    }


}