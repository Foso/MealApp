# MealApp

This is an example project that is using a shared Kotlin Multiplatform project between an Android and an iOS project. The Android App UI is using Jetpack Compose  and the iOS App is using SwiftUI. Please be aware that i just started learning Compose and SwiftUI, so there are a lot of things to improve.

| Android Overview | Details1 |  Details2 |
| ------------------ | --------------------------- | ------------------ |
|<img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/Android1.png" height="400" alt="Screenshot"/> | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/Android2.png" height="400" alt="Screenshot"/>  | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/Android3.png" height="400" alt="Screenshot"/> |


| iOS Overview | Details1 |  Details2 |
| ------------------ | --------------------------- | ------------------ |
|<img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/ios1.png" height="400" alt="Screenshot"/> | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/ios2.png" height="400" alt="Screenshot"/>  | <img src="https://raw.githubusercontent.com/Foso/MealApp/master/docs/ios3.png" height="400" alt="Screenshot"/> |

## iOS
Have tested it on XCode v12.

Just import the iOS project [iosApp](iosApp/) inside Xcode.

The iOS project compiles a Kotlin module to a framework (see [iosApp](iosApp/)). 

The iOS application is written with SwiftUI. It uses Kotlin module as a library.
Kotlin module is built into Objective-C framework by invoking Gradle
from custom "Run Script" build phase, and this framework is imported into
the Xcode project.

## Android 
The Android App is using Jetpack Compose for UI
You need to use [Android Studio Canary](https://developer.android.com/studio/preview) version. 

## Shared
The apps get their data from the Meal Api (https://www.themealdb.com). The complete Network Layer and Json Serialization for both apps is done in this module. It is using Ktor Clients and Kotlinx Serialization

### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

## 📜 License

-------

This project is licensed under Apache License, Version 2.0

    Copyright 2021 Jens Klingenberg

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

