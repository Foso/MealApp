pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "NewCoktailapp"


include(":androidApp")
include(":shared")

enableFeaturePreview("GRADLE_METADATA")
