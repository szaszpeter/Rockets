# Rockets
Sample application consuming the [Space-X Rest API](https://github.com/r-spacex/SpaceX-API) to display a list of rockets and the technical details of them.

## Features
- US1 - List of Rockets, Welcome Screen (Only shown on first opening)
- US2 - Filter rockets by Active/Inactive
- US3 - Rocket Details Screen 
- US4 - Animations, Unit Tests (RocketRepository, LaunchRepository)


## Technologies / Patterns
- Project modularisation: Data, Domain, Presentation, Common
- Clean Architecture Concepts
- Dependency Injection Setup with Hilt
- MVVM Pattern
- Use Cases for managing data flow
- Retrofit for Networking
- Jetpack Compose for UI 
- Navigation Component 

## Usage
- Clone this repository on your local machine. 
- Open the project in Android Studio. 
- Build the project and run it on your emulator/device.


&nbsp;

Rocket List            |  Rocket Details
:-------------------------:|:-------------------------:
 <img src="https://user-images.githubusercontent.com/5732276/180953910-7d249f10-d88d-4d03-8d36-9bfe1e001e9e.png" width="400"> |  <img src="https://user-images.githubusercontent.com/5732276/180953934-b4081255-4227-4783-9465-c06006b78d30.png" width="400">



## Sources of inspiration

- https://github.com/android/architecture-samples - Architecture Blueprints
- https://johncodeos.com/how-to-add-search-in-list-with-jetpack-compose/
- https://compose.academy/blog/building_a_multi-action_floating_action_button_in_jetpack_compose
