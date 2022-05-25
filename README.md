# weatherapp
Forecast application
A simple application built to demonstrate the use of modern android architecture component with MVVM Architecture 🏗
The Application is using the OpenWeatherMapsAPI to searching weather by city name.

Apply SOLID principles

Project has 3 branches:
1. MVVM: Structure to which mvvm design pattern can be applied
2. Clean Architect (3 layers: data, domain, presentation): Clean architecture-oriented structure in mvvm design pattern (without modules)
3. Clean Architect and Modular Pattern (data, domain, presentation): Clean architecture-oriented structure in mvvm design pattern and Modular pattern


Build With: 
Kotlin - Programming language for Android
Hilt-Dagger - Standard library to incorporate Dagger dependency injection into an Android application.
Retrofit - A type-safe HTTP client for Android and Java.
Room - SQLite object mapping library.
Coroutines - For asynchronous
LiveData - Data objects that notify views when the underlying database changes.
ViewModel - Stores UI-related data that isn't destroyed on UI changes.
ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
Work Manager - manage caching mechanism & lifecycle
Junit, Mockito, Room Testing - Write unit test


The application has finished features:
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

