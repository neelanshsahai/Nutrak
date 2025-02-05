# Nutrak (A nutrition tracking app)

## Setup instruction
* Enable Developer Options in your Android Device
* Load Project in Android Studio, and connect the android phone using USB
* Run the app in the Android device

## Libraries Used
* Camera Libraries - To implement scanner screen of the app
* Dagger Hilt - To implement Dependency Injection in the app
* Navigation Compose - To implement Navigation between composable as screen
* Material3 Icons Extended - To use the extended set of Icons provided by M3
* Number Picker - A simple Number Picker Library to select numbers via scrolling

## Architecture
As of now, App is divided into two layers (viz., UI Layer and Data Layer).
Since this is a static app right now which works on Mock data, I haven't created a Repository or Network directory.

The `ui` directory consists of the following things
* `ui.screens` : A directory holding all the Composables that act as a Whole Screen (For eg., Splash Screen, Login Screen, Dashboard Screen's Home Page, etc.)
* `ui.common` : A directory holding all the Composable elements that are common throughout the project and will be re-used in different screen (For eg., Toolbar, Button, etc.)
* `ui.theme` : Directory holding a custom AppTheme, Custom AppColors, and AppTheme's Skeleton that can be used throughout the app
* `ui.navigation` : This holds the Navigation Composable which is responsible for all the navigation in the app
* `ui.viewmodels` : Has all the view models that are responsible for communication between the UI Composables and the Business Logic. In this case viewmodels holds the logic for populating mock data, updating Ui States, and validating user inputs, etc.

The `data` directory has the following things
* `data.di` : Holds the app module which is responsible to generate the dependency graph and provide dependencies throughout the app.

The `utils` directory has the following files
* `ExtensionFunctions` : Holds all the extension functions for the project that can be used for utility
* `constants` : Holds all the constant values that will be used across the app.

## App Flow

Here is how the Nutrak App looks like :

| Splash Screen | Intro Page 1 | Intro Page 2 | Intro Page 3 |
|:--------:|:--------:|:--------:|:--------:|
|![Splash Screen](screenshots/Splash%20Screen.jpeg)|![Login Page](screenshots/Intro%20Pager%201.jpeg)|![Password Page](screenshots/Intro%20Pager%202.jpeg)|![OTP Screen](screenshots/Intro%20Pager%203.jpeg)|

| SignUp Page | Login Screen | Password Screen | OTP Screen |
|:--------:|:--------:|:--------:|:--------:|
|![Splash Screen](screenshots/SignUp%20Screen.jpeg)|![Login Page](screenshots/SignIn%20Screen.jpeg)|![Password Page](screenshots/Password%20Screen.jpeg)|![OTP Screen](screenshots/OTP%20Screem.jpeg)|

| Welcome Screen | Gender Page | Age Page | Height Page |
|:--------:|:--------:|:--------:|:--------:|
|![Splash Screen](screenshots/Welcome%20Screen.jpeg)|![Login Page](screenshots/Gender%20Page.jpeg)|![Password Page](screenshots/Age%20Page.jpeg)|![OTP Screen](screenshots/Height%20Page.jpeg)|

| Weight Page | Goal Page | Preference Page | Dashboard's Home Tab |
|:--------:|:--------:|:--------:|:--------:|
|![Splash Screen](screenshots/Weight%20Page.jpeg)|![Login Page](screenshots/Goal%20Page.jpeg)|![Password Page](screenshots/Preference%20Page.jpeg)|![OTP Screen](screenshots/Dashboard%20Home%20Tab.jpeg)|

| Dashboard's Streaks Tab | Scanner Tab | Loader Screen | Nutrition Results Page |
|:--------:|:--------:|:--------:|:--------:|
|![Splash Screen](screenshots/Dashboard%20Streaks%20Tab.jpeg)|![Login Page](screenshots/Dashboard%20Camera%20Tab.jpeg)|![Password Page](screenshots/Loader%20Screen.jpeg)|![OTP Screen](screenshots/Nutrition%20Result.jpeg)|
