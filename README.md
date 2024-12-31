# Android (Full Implementation)

<img src="android_loading.png" alt="drawing" width="200"/>
<img src="android_success.png" alt="drawing" width="200"/>
<img src="android_error.png" alt="drawing" width="200"/>

## Build

+ Open Android Studio
+ File > Open > Select <PreffectTakeHomeRoot>/android folder > Ok/Open
+ File > Sync Project with Gradle Files
+ Build > Make Project

## Deploy application

### Create AVD or connect your phone

+ To create a new android virtual device, select Tools > Device Manager
+ Select Create Virtual Device
+ In the Virtual Device Configuration, select Pixel 9 > Next
+ Select the VanillaIceCream API 35 release with Google Play > Next
+ Finish

### Run app on device
+ Run > Select Device...
+ Select your device/AVD
+ Run > Run 'app'

## Run unit tests

+ File > Sync Project with Gradle Files
+ In the Project view panel on the left, select "Project" from the dropdown.
+ Right click on the "android/app/src/test" folder and select "Run 'Tests in Preffect_Fitness_Tracker'" 

## Tehcnical Overview

The application is written using an MVVM architecture. 
+ The view and viewmodel are under "com.ottmatt.preffectfitnesstracker.ui.fitnesstracker"
+ The models are located in "com.ottmatt.preffectfitnesstracker.persistence" and "com.ottmatt.preffectfitnesstracker.repository"
+ The persistence layer handles fetching data from remote and local data sources, such as the network and google fit.
+ The repository abstracts/consolidates the data sources from the viewmodel and moves the work off the main thread.
+ The viewmodel takes the data from the repository and transforms it into something useable by the views.
+ Some important libraries are Hilt/Dagger, which provide the dependency injection framework to generate our dependencies. Coroutines library, which is the kotlin standard of multi-threading. Ktor, which is the kotlin native http client. Compose, which is the view framework provided by android (alternatively could have used xml).

## Additional TODO
+ Swap the FitnessStubbedDataSource with one that fetches step count from the Health Connect API.
+ Modularize the app into features to support a larger project. Currently, it's a simple screen and doesn't need a complex architecture. If we were to add more features, we would refactor the data, common and UI features into sepearte android modules. This modularization would help multiple engineers work on separate parts of each app without disturbing each other's work. It would also enable custom Play Store updates and app delivery.
+ Adding local persistence to each application, such as Room and SwiftData. This lets us quickly show the user a populated UI while giving the app time to fetch updates.
+ Implement a CI/CD pipeline to run tests, build and deploy the applications to their respective stores. The features of this CI/CD are described in the CI/CD section of the README.
+ Write more tests for better coverage.
+ Potentially implement Service or WorkManager for long running network requests. E.g. if our network requests take some long period of time, we can run them in the background, populate our DB, and update the UI with the DB as the source of data.
+ Perform app profiling to optimize/find slow tasks.
+ Use proguard/R8 to minify and obfuscate our final dex code.

# iOS

T.B.D.