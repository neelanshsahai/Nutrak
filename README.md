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

<table width="100%">
  <tr>
    <th width="25%">Splash Screen</th>
    <th width="25%">Intro Page 1</th>
    <th width="25%">Intro Page 2</th>
    <th width="25%">Intro Page 3</th>
  </tr>
  <tr align="center">
    <td width="25%"><img src="screenshots/Splash%20Screen.jpeg" alt="Splash Screen"></td>
    <td width="25%"><img src="screenshots/Intro%20Pager%201.jpeg" alt="Intro Page 1"></td>
    <td width="25%"><img src="screenshots/Intro%20Pager%202.jpeg" alt="Intro Page 2"></td>
    <td width="25%"><img src="screenshots/Intro%20Pager%203.jpeg" alt="Intro Page 3"></td>
  </tr>
</table>

<table width="100%">
  <tr>
    <th width="25%">SignUp Page</th>
    <th width="25%">Login Screen</th>
    <th width="25%">Password Screen</th>
    <th width="25%">OTP Screen</th>
  </tr>
  <tr align="center">
    <td width="25%"><img src="screenshots/SignUp%20Screen.jpeg" alt="SignUp Screen"></td>
    <td width="25%"><img src="screenshots/SignIn%20Screen.jpeg" alt="Login Page"></td>
    <td width="25%"><img src="screenshots/Password%20Screen.jpeg" alt="Password Page"></td>
    <td width="25%"><img src="screenshots/OTP%20Screem.jpeg" alt="OTP Screen"></td>
  </tr>
</table>

<table width="100%">
  <tr>
    <th width="25%">Welcome Screen</th>
    <th width="25%">Gender Page</th>
    <th width="25%">Age Page</th>
    <th width="25%">Height Page</th>
  </tr>
  <tr align="center">
    <td width="25%"><img src="screenshots/Welcome%20Screen.jpeg" alt="Welcome Screen"></td>
    <td width="25%"><img src="screenshots/Gender%20Page.jpeg" alt="Gender Page"></td>
    <td width="25%"><img src="screenshots/Age%20Page.jpeg" alt="Age Page"></td>
    <td width="25%"><img src="screenshots/Height%20Page.jpeg" alt="Height Page"></td>
  </tr>
</table>

<table width="100%">
  <tr>
    <th width="25%">Weight Page</th>
    <th width="25%">Goal Page</th>
    <th width="25%">Preference Page</th>
    <th width="25%">Dashboard's Home Tab</th>
  </tr>
  <tr align="center">
    <td width="25%"><img src="screenshots/Weight%20Page.jpeg" alt="Weight Page"></td>
    <td width="25%"><img src="screenshots/Goal%20Page.jpeg" alt="Goal Page"></td>
    <td width="25%"><img src="screenshots/Preference%20Page.jpeg" alt="Preference Page"></td>
    <td width="25%"><img src="screenshots/Dashboard%20Home%20Tab.jpeg" alt="Dashboard Home Tab"></td>
  </tr>
</table>

<table width="100%">
  <tr>
    <th width="25%">Dashboard's Streaks Tab</th>
    <th width="25%">Scanner Tab</th>
    <th width="25%">Loader Screen</th>
    <th width="25%">Nutrition Results Page</th>
  </tr>
  <tr align="center">
    <td width="25%"><img src="screenshots/Dashboard%20Streaks%20Tab.jpeg" alt="Dashboard Streaks Tab"></td>
    <td width="25%"><img src="screenshots/Dashboard%20Camera%20Tab.jpeg" alt="Scanner Tab"></td>
    <td width="25%"><img src="screenshots/Loader%20Screen.jpeg" alt="Loader Screen"></td>
    <td width="25%"><img src="screenshots/Nutrition%20Result.jpeg" alt="Nutrition Results Page"></td>
  </tr>
</table>
