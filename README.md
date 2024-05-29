![Hafthashtad]

Hafthashtad App
==================

**Hafthashtad** is a fully functional Android app built entirely with Kotlin and Jetpack Compose.

# Features

**Hafthashtad** displays content from the
[Hafthashtad Products](https://fakestoreapi.com/products) series. Users can browse for content and search in titles.

# Development Environment

**Hafthashtad** uses the Gradle build system and can be imported directly into Android Studio (make sure you are using the latest stable version available [here](https://developer.android.com/studio)). 

Change the run configuration to `app`.

![image](https://user-images.githubusercontent.com/873212/210559920-ef4a40c5-c8e0-478b-bb00-4879a8cf184a.png)

The `prodDebug` and `prodRelease` build variants can be built and run .

![image](https://user-images.githubusercontent.com/873212/210560507-44045dc5-b6d5-41ca-9746-f0f7acf22f8e.png)

# Architecture

The **Hafthashtad** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture)

# Modularization

The **Hafthashtad** app has been fully modularized .

# Build

The app contains the usual `debug` and `release` build variants. 

**Hafthashtad**.

The app also uses
[product flavors](https://developer.android.com/studio/build/build-variants#product-flavors) to
control where content for the app should be loaded from.

The `demo` flavor uses static local data to allow immediate building and exploring of the UI.

The `prod` flavor makes real network calls to a backend server, providing up-to-date content. At 
this time, there is not a public backend available.

For normal development use the `demoDebug` variant. For UI performance testing use the
`demoRelease` variant. 

# UI
The app was designed using [Material 3 guidelines](https://m3.material.io/).

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/jetpack/compose). 

Each theme also supports dark mode. 

The app uses adaptive layouts to
[support different screen sizes](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes).
