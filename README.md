# Chronos
<p align="center">
A native android application built in kotlin with it's UI entirely in Jetpack Compose.
</p>

## Description
<p align="center">
Chronos demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack (Room, ViewModel, Jetpack Compose), and is based on MVVM architecture.
It is a timezone converter app that lets the user save live time of different locations as well as convert time from one
location to another. It also displays live time of the user's location.
</p>

## Screenshots

### Dark mode
<img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.49.44.png" width="125" height="250" /> <img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.50.03.png" width="125" height="250" />
<img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.49.59.png" width="125" height="250" /> <img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.50.08.png" width="125" height="250" />
<img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.50.21.png" width="125" height="250" />

### Light mode
<img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.49.50.png" width="125" height="250" /> <img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.50.04.png" width="125" height="250" />
<img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.49.56.png" width="125" height="250" /> <img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.50.18.png" width="125" height="250" />
<img src="https://github.com/Czeach/Chronos/blob/main/screenshots/photo_2023-02-26%2019.50.24.png" width="125" height="250" />


## Tech stack and Libraries used
* Minimum SDK level 23
* Kotlin, 100% Jetpack Compose, [Coroutines](https://developer.android.com/kotlin/coroutines) + Flow for asynchronous programming
* Jetpack
    * [Jetpack Compose](https://developer.android.com/jetpack/compose): Android’s modern toolkit for building native UI
    * [Navigation Compose](https://developer.android.com/jetpack/compose/navigation): Navigate between composables while leveraging of the Navigation component’s infrastructure and features
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Encapsulates related business logic and manage UI data in a lifecycle-aware fashion
    * [Room](https://developer.android.com/training/data-storage/room): Persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite
    * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android): Standard way to incorporate Dagger dependency injection into an Android application that reduces boilerplate code
* Architecture
    * MVVM Architecture
    * Repository pattern
* [Retrofit2](https://github.com/square/retrofit): Type-safe REST client for Android and Java
* [Gson](https://github.com/google/gson): Java library that can be used to convert Java Objects into their JSON representation and vice versa


## Architecture
Chronos follows [Google's official architecture guidance](https://developer.android.com/topic/architecture). It is based on the MVVM architecture and the Repository pattern.

## APIs used

### Abstract API
Chronos uses the [Abstract Timezone API](https://app.abstractapi.com/api/timezone/documentation) for RESTful API services.
It makes use of the ```current_time``` an ```convert_time``` endpoints from the API.

### Places API
Chronos also makes use of [Google's Places API](https://developers.google.com/maps/documentation/places/web-service/overview).
It uses the place autocomplete service to get place predictions



## LICENSE
```

Apache License

Copyright 2022 Ezichi Amarachi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
