# Movies-Demo-Clean
### About the project
Demo Application to demonstrate clean architecture and written  display list of movies from [Movies API](https://movies-mock-server.vercel.app/movies) 

### Tools used
Android Studio Flamingo | 2022.2.1, Written in kotlin

### Architecture

This application follows clean architecture multi module approach to have unidirectional data flow, separation of concern, testability, readable and maintainable.
* Modules
  * app  - The app layer contains the base application, activitity and navigation graph.
  * domain - The domain layer contains the UseCases that encapsulate a single and very specific task that can be performed. (cann't access any other module)
  * data - The data layer implements the repository that the domain layer defines. This layer provides single source of truth for data.(can only access domain module) 
  * feauture-movies - The feaure module contains all code related to movies features.
  * common-ui  - The common ui layer contains the base UI classes for other feature modules. 
  * shared-test - The shared test layer contains the base or common testing classes. Feautue modules can be added as dependeny to access shared test code and shared-test module should be added as test implementation project in the feature modules.  
* MVVM architecture pattern
* BuildSrc: Directory for centralized dependency management (Drawback: Whenever there is a change in library version, whole project will rebuild. This is a drawback in project which uses many feauture modules). BuildSrc can be replaced with [Gradle version catalog](https://developer.android.com/build/migrate-to-catalogs)

### Library used
  * Kotlin
  * Coroutines - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
  * Flow - Flow is used to pass (send) a stream of data that can be computed asynchronously
  * Stateflow - Stateflow is a state-holder observable flow that emits the current and new state updates to its collectors
  * Dagger-Hilt - for dependency injection.
  * Kotlin-DSL - Used to handle gradle dependencies and config versions
  * JetPack
    * ViewModel - Stores UI-related data that isn't destroyed on UI changes.
    * Navigation - Used to navigate between fragments
    * Data Binding - Used to bind UI components in your XML layouts.
    * Material-Components - Material design components like cardView.
  * Retrofit - Used for REST api communication.
  * OkHttp - HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
  * Moshi - Used to convert Java Objects into their JSON representation and vice versa.
  * Glide - Glide is a fast and efficient image loading library for Android 
  * [Turbine](https://github.com/cashapp/turbine) - library offers a convenient API for creating a collecting coroutine, as well as other convenience features for     testing Flows



