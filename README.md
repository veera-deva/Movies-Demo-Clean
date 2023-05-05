# Movies-Demo-Clean
### About the project
Demo Application to demonstrate clean architecture and written  display list of movies from [Movies API](https://movies-mock-server.vercel.app/movies) 

### Tools used
Android Studio Flamingo | 2022.2.1, Written in kotlin

### Architecture

This application follows clean architecture multi module approach to have unidirectional data flow, separation of concern, testability, readable and maintainable.
* Modules
  * app(Presentation),
  * domain
  * data
  * feauture-model (not used)
* MVVM architecture pattern
* BuildSrc: For centralized dependency management (Drawback: Whenever there is a change in library version whole application will rebuild. This is a drawback in project which uses many feauture modules)

### Library used
  * Kotlin
  * Coroutines - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously
  * Flow - Flow is used to pass (send) a stream of data that can be computed asynchronously
    Stateflow - Stateflow is a state-holder observable flow that emits the current and new state updates to its collectors
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



