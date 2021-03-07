# MealApp

This is an example project that is using a shared Kotlin Multiplatform project between an Android and an iOS project. The Android App is using Jetpack Compose for UI and the iOS App is using SwiftUI. 


## iOS
The iOS project compiles Kotlin module to a framework (see [iosApp](iosApp/)). The framework can be easily included in an existing iOS project (e.g. written in Swift or Objective-C)

The iOS application is written in Swift. It uses Kotlin module as a library.
Kotlin module is built into Objective-C framework by invoking Gradle
from custom "Run Script" build phase, and this framework is imported into
the Xcode project.

### Building
You need to use [Android Studio Canary](https://developer.android.com/studio/preview) version.  Have tested it on XCode v12.
