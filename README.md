# news-app

Introduction
------------

- The application is written fully in Kotlin and uses [Clean Architecture based on MVVM and Repository patterns](https://developer.android.com/jetpack/docs/guide). It also implements [MVI architecture](https://www.raywenderlich.com/817602-mvi-architecture-for-android-tutorial-getting-started) in it’s Presentation layer, one of the newest architecture patterns for Android, inspired by the unidirectional and cyclical nature of the Cycle.js framework.

- Android Jetpack components are used to sustain the architectures, some components that are used: ViewModel, MutableLiveData, Lifecycles, Navigation component, BottomNavCom...

- The application gets the data from [NewsApi](https://newsapi.org/), using OKHttp, Retrofit and Moshi to parse it. OkHttp cache implemented. 

- Pagination was also made utilising a custom implementation of the [Epoxy component](https://github.com/airbnb/epoxy).

- Kotlin Coroutines are used for background tasks and multitasking.

- Koin is used for dependency injection.

- [Lottie](https://github.com/airbnb/lottie-android) is used for animations.

- The project hosts 9 modules each serving a distinguished purpose (Figured from the name).

- The main app hosts one activity and 5 fragments connected with the Jetpack navigation component.


Getting Started
---------------
- Getting [Android Studio IDE](https://developer.android.com/studio)
- Import project
- Run Gradle Wrappers / Project Tasks `gradlew build` `
* `gradlew test` - and for tests run

Hardware And Operating system (Check the AndroidSettings.kt file in BuildSrc Module)
--------------

- Android Studio 4.1.2
- MacOS Mojave 10.14.5
- Minimum Android OS Supported 6.0
- Android OS Targeted 11
- Kotlin version 1.4.32

Libraries (Check the Dependency.kt file in BuildSrc Module)
---------------

Future Improvements
---------------
- Add more test cases to cover the maximum codebase.
- Add Detekt for code sniffing.
- A bit less abstraction to have faster development time.
- Some issues setting Kotlin DSL.
- Time loss in finding the correct size of icons.
- Some issues with using the newest version of libraries.
- Add shared transitions.
- Add some GitHub Actions as a CI/CD

=> For further questions email me at najihabdelhadi@gmai.com
