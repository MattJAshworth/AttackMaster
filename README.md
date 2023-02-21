
# Attack Master

Attack Master is my most successful project to date and rides on the success of the MoonActive game 'Coin Master'.

Attack Master collates and provides links from across MoonActive's social media and email newsletters that provide in game bonuses for Coin Master. 

Written natively for Android in Java with Android Studio. 

Attack Master accrued over 3 million downloads, over 100,000 DAU (daily active users) and 400,000 WAU (weekly active users) with ads being served from Admob and Unity Ads for mediation performance.


<p float="left">
  <img src="https://github.com/MattJAshworth/AttackMaster/blob/master/media/device-2020-05-19-135209.png?raw=true" width="180" />
  <img src="https:/github.com/MattJAshworth/AttackMaster/blob/master/media/device-2020-05-19-135523.png?raw=true" width="180" /> 
  <img src="https://github.com/MattJAshworth/AttackMaster/blob/master/media/device-2020-05-19-135156.png?raw=true" width="180" />
</p>

## Release Statistics

Due to technical and developer agreement issues the project went through 2 rebrandings including 1 redesign.

- Daily Free Spins & Coins for Coin Master
	- Attack Master
		- Reward Master
		
<p float="left">
  <img src="https://github.com/MattJAshworth/AttackMaster/blob/master/media/de8434a54232f1296d66972ecc092ee3133f95c64dc428809c5ead2a04e66995.jpg?raw=true" width="180" />
  <img src="https://github.com/MattJAshworth/AttackMaster/blob/master/media/c2bad6e7c98bf53c76462457db9a9d2d737d60387725cbdfe78ce2f4cdb5a8ad.jpg?raw=true" width="180" /> 
  <img src="https://github.com/MattJAshworth/AttackMaster/blob/master/media/5cef12052328d7995ec5e44b2b3eb8bb802291ee33b322a35d95cf7065a25249.jpg?raw=true" width="180" />
</p>

### Achievements
* Ranked 6th in Trending Tools for multiple weeks
* In the top 50 trending apps
* 100,000+ Daily Active Users
* 400,000+ Weekly Active Users

## Usage

This is an Android Studio Project. The project can be built within Android Studio using the 'build.gradle' file. Alternatively the app can be run on device or emulator using the release apk found under '/app/release/'. 

Please note that the project is no longer maintained and access to the Firebase datastore has been removed from the project and is inaccessible in-app. The underlying netcode still remains should you want to learn from it. 

Furthermore I heavily advise against reconstructing the firebase store and publishing the project on Google Play. For whatever reason there is some part of the application that breaks the Google Play Developer Agreement and will result in your developer account being suspended or immediately shut down.

## Features:
* Fetch the 10 most recent reward links from the Firebase server
* Link availability/ status is denoted by a coloured dot on each element. Implemented because reward links expire after 72 hours.
	* Green = New
	* Orange = 1 day old
	* Red = Many days old
* A date tag is also appended to each element to further clarify the previous point.
* Send intent to Coin Master app with reward data.
* Share the reward with friends via dedicated button.
* Ability to report a reward link as broken (UI implemented only)
* Links to Twitter, Instagram, Google Play and the Privacy Policy are hidden in the toolbar menu.
* Remove ads for a one time payment.

## Tech Stack
* Android Studio IDE
* ADT (Android Developer Tools)
* Android SDK
* Java
* XML
* Firebase

## Notes
This is an incomplete project. Code and API keys have been removed from the project for privacy. You will need to create a Firebase datastore and add Admob or Unity dependencies and keys where required. 

Once again I heavily advise against reconstructing the firebase store and publishing the project on Google Play. For whatever reason there is some part of the application that breaks the Google Play Developer Agreement and will result in your developer account being suspended or immediately shut down.

## Contact - Matt J Ashworth
* [Linkedin](https://linkedin.com/in/MattJAshworth)
* [Twitter](https://twitter.com/MattJAshworth)
* [Email](mailto:contact@mattjashworth.xyz)
* [Website](https://mattjashworth.xyz)
