# weatherapp
Forecast application
A simple application built to demonstrate the use of modern android architecture component with MVVM Architecture.
The Application is using the OpenWeatherMapsAPI to searching weather by city name.


Apply SOLID principles


Project has 3 branches:
1. MVVM: Structure to which mvvm design pattern can be applied
2. Clean Architect (3 layers: data, domain, presentation): Clean architecture-oriented structure in mvvm design pattern (without modules)
3. Clean Architect and Modular Pattern (data, domain, presentation): Clean architecture-oriented structure in mvvm design pattern and Modular pattern



----->   This is Clean Architect and Modular Pattern branch:

Clean architect with 4 modules: app, data, domain, presentation

+  app has a green circle on the folder indicating that it is an Android application module
+  data and presentation are Android library modules
+  domain is a pure Java/Kotlin library

Structure Application:

Project has 3 Gradle modules:

+  app — Android application module
  
dependencies {

    implementation project(path: ':domain')
    
    implementation project(path: ':data')
    
    implementation project(path: ':presentation')
    
    ...
    
}

+  presentation — Android library modules
  
dependencies {

    implementation project(path: ':domain')
    
    implementation project(path: ':data')
    
    ...
    
}


+  data— Android library module
  
dependencies {

    implementation project(path: ':domain')
    
    ...
    
}


+  domain — Java library module
  
Deatail:


1.    app : module depends on all of the other three modules (data, domain, presentation)
      +  di
      +  Application file

2.   data: module depends on domain module

      +  local

      +  mapper

      +  remote

      +  repository

      +  workmanager
 
3.   domain

      +  model
 
      +  repository
 
      +  usecase
   
4.   presentation

      +  base
 
      +  ui
 
      +  utils


Build With:
1. Kotlin - Programming language for Android
1. Hilt-Dagger - Standard library to incorporate Dagger dependency injection into an Android application.
3. Retrofit - A type-safe HTTP client for Android and Java.
4. Room - SQLite object mapping library( cache ).
5. Coroutines - For asynchronous.
6. LiveData - Data objects that notify views when the underlying database changes.
7. ViewModel - Stores UI-related data that isn't destroyed on UI changes.
8. ViewBinding - Generates a binding class for each XML layout file present in that module 
   and allows you to more easily write code that interacts with views.
9. Work Manager - manage caching mechanism & lifecycle.
10. Junit, Mockito, Room Testing - Write unit test.



The application has finished features:
1. The application is a simple Android application which is written by Java/Kotlin.
2. The application is able to retrieve the weather information from OpenWeatherMapsAPI.
3. The application is able to allow user to input the searching term.
4. The application is able to proceed searching with a condition of the search term length 
   must be from 3 characters or above.
5. The application is able to render the searched results as a list of weather items.
6. The application is able to support caching mechanism so as to prevent the app from
   generating a bunch of API requests.
7. The application is able to manage caching mechanism & lifecycle.
8. The application is able to handle failures.
9. The application is able to support the disability to scale large text for who can't see the text
   clearly.
10. Unit test for Database (Room)


   













