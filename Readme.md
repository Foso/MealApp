# MealApp

This is an example project that is using a shared Kotlin Multiplatform project between an Android and an iOS project. The Android App is using Jetpack Compose for UI and the iOS App is using SwiftUI. 

| Android Overview | Details1 |  Details2 |
| ------------------ | --------------------------- | ------------------ |
|<img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/Android1.png" height="400" alt="Screenshot"/> | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/Android2.png" height="400" alt="Screenshot"/>  | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/Android3.png" height="400" alt="Screenshot"/> |


| iOS Overview | Details1 |  Details2 |
| ------------------ | --------------------------- | ------------------ |
|<img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/ios1.png" height="400" alt="Screenshot"/> | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/ios2.png" height="400" alt="Screenshot"/>  | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/ios3.png" height="400" alt="Screenshot"/> |

## iOS

The iOS project compiles Kotlin module to a framework (see [iosApp](iosApp/)). 

The iOS application is written with SwiftUI. It uses Kotlin module as a library.
Kotlin module is built into Objective-C framework by invoking Gradle
from custom "Run Script" build phase, and this framework is imported into
the Xcode project.

## Android 
The Android App is using Jetpack Compose for UI

## Shared
The apps get their data from the Meal Api (https://www.themealdb.com). The complete Network Layer and Json Serialization for both apps is done in this module. It is using Ktor Clients and Kotlinx Serialization

### Building
#### Android
You need to use [Android Studio Canary](https://developer.android.com/studio/preview) version. 

#### iOS
Have tested it on XCode v12.
