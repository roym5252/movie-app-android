
![Movie App](https://github.com/roym5252/movie-app-android/assets/30793139/8363b202-220c-4730-b0ae-b2043b3815f0)

# Movie App

This is a sample for demonstrating the Modern Android App Architecture. The app communicates with '[www.omdbapi.com](http://www.omdbapi.com/)' for fetching all movies which has the word 'time' in the title. User registration and login operations are done locally by communicating with local database. Biometric authentication is added for additional security.

## **Main features:**

* Login

* Registration

* Movie Listing (Pagination)

* Biometric authentication

## **Architecture**

**Modern Android App Architecture** is used because it clearly separates the app into three layers: UI, Domain, and Data. This makes it easy to write unit tests for the Domain layer, and to keep all UI elements in the UI layer.

A core module is created to keep all common code, and separate modules are created for each screen. This structure allows new modules to be added based on features in the future, and keeps module-specific UI elements within the related module. All common code is kept under the core module.

This structure also makes it easy to convert the project to a multi-module project in the future without having to make drastic changes.

**Benefits:**

* Future scalability
* Code maintainability
* Separation of layers

## **Technologies:**

* Hilt
  
* ViewModel

* Coroutines

* Retrofit
  
* Moshi

* OkHttp

* HttpInterceptor

* Glide

* Paging 3

* EncryptedSharedPreferences

* Room Library

* SQLite

* AndroidX Biometric Library

* Espresso
  
* Robolectric
  
* AndroidX Test

* Mockito

* MockWebServer

## API key configuration

- Open keys.c in the below path:

  app/src/main/jni/keys.c
  
- Replace ‘ADD API KEY HERE’ with API key generated from 'www.omdbapi.com' in the below line.

  return (*env)-> NewStringUTF(env, "ADD API KEY HERE");
