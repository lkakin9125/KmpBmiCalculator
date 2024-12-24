# Objective

It is a demo project to present 
1. how to create ui application with TDD 
2. by using Kotlin Multiplatform, make all the business logic to multi platform and create the UI natively in Android, iOS and Web

and [here](https://docs.google.com/presentation/d/1HxGc7Xp7a2wajeEf6UhCYNKjbVhtDux0Di5LMArbGtM/edit#slide=id.p) is the presentation Google Slide. It may help to understand the design of this project

# Android 

it is a normal Android APP with compose and multi modules.

## App build

1. use Android Studio to open the project root
2. select the run configuration as `androidApp` and press run

## Running unit test in Android Environment

1. use Android Studio to open the project root
2. right click to the `shared/src/commonTest` and click the run test option.
3. if your device is in Mac, an option popup may show up. please select `android`

# iOS

It is a iOS that using SwiftUi develop the APP layout. 
It is possible to develop by other layout framework but using SwiftUI want to demo the native behaviour in iOS
It can be run by different IDE. it depends on what is the need.
Normally, Android Studio is used of updating the logic and running unit test since the logic are in Kotlin
and for UI update, Xcode is recommended 

## Android Studio

1. in MacOS Device, use Android Studio to open the project root 
2. select the run configuration as `iosApp` and press run
3. if encounter gradle permission issue, please run command `chmod +x gradlew`

## Running unit test in iOS Environment

1. in MacOS Device, use Android Studio to open the project root
2. right click to the `shared/src/commonTest` and click the run test option.
3. an option popup may show up. please select `iosSimulator`

## Xcode

1. open the `${project_root}/iosApp` at Xcode
2. config the Xcode simulator and press the run button

# Web (JS)

It is a Web APP using ReactJS to develop the layout.
It is possible to develop by other layout framework. Choosing ReactJs is because it is one of the most common framework in Web.

It can be run by different IDE. it depends on what is the need.
Normally, Android Studio is used of updating the logic and running unit test since the logic are in Kotlin
and for UI update, Visual Studio Code is recommended.

## Android Studio running unit test

1. use Android Studio to open the project root
2. click the right hand side gradle icon and select `BmiCalculator > shared > Tasks > other > jsNodeTest`
3. OR the test can be run by command `./gradlew jsNodeTest`

## Running Web APP

1. use Visual Studio Code to open the `${project_root}/web_app/bmi_cal_web_app`, eg by command `code ${project_root}/web_app/bmi_cal_web_app`
2. run command `npm install` to install missing npm module
3. run command to fetch the kmp exported JS library
   1. if in Windows, please run `npm run win-fetch-kmp`
   2. if in Mac, please run `npm run mac-fetch-kmp`
4. run command `npm start` to start the web app server