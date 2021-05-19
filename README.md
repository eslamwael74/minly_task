# Minly Assignment

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>


<!-- ABOUT THE PROJECT -->
## About The Project
Minly development assignment, That is simple photo upload application


<!-- Built With -->
### Built With
* [NestJs](https://docs.nestjs.com/)
* [Android](https://developer.android.com/)
* [Flutter-iOS Cupertino Style](https://flutter.dev/docs/development/ui/widgets/cupertino)


<!-- GETTING STARTED -->
## Getting Started
To get a local copy up and running follow these simple example steps.

### Prerequisites
* Node
[Node Install](https://nodejs.org/en/)

* NgRok
  ```sh
   npm install ngrok -g
  ```
* Android
[Android Install](https://developer.android.com/studio/install)

* Flutter
[Flutter Install](https://flutter.dev/docs/get-started/install)


<!-- USAGE -->
## Usage
### Backend Setup

```bash
npm install
npm run start
ngrok http 3000
```
* Import Insomnia file from root folder ```/minly_task/blob/main/Insomnia_2021-05-19.json```

### Android Setup
* Open file ```app/build.gradle```

* Navigate to ```android.defaultConfig.buildConfigField```

* Change base URL  ```buildConfigField "String", "BASE_URL", "\"$ngRokOutputUrl\""```

### Flutter Setup
* Open file ```lib/utils/constants.dart```

* change ```static const baseUrl = "$ngRokOutputUrl";```
