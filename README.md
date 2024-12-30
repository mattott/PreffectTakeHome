# Android
The Android application is the fully implemented solution to the project. 

+ The architecture of the app allows for easy swapping of data sources, whether that be for testing purposes or implementing Health Connect/Google Fit APIs.
+ A few unit tests were written for the ViewModel, but there is more that can be tested. For example, we can test compatibility with devices which do('nt) have the Health Connect app installed using Firebase Test Lab.
+ CI/CD was not impelemented due to time constraints.
+ Local persistence/storage was not implemented due to time constraints.
+ Assumptions were made about the speed with which data is fetched from the network. In long-running tasks, we would use a foreground service to prevent the system from killing our work.

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

# iOS

T.B.D.