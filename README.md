# Project 1 - Instagram Client

Instagram Client is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: 8 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current popular photos** from Instagram
* [x] For each photo displayed, user can see the following details:
  * [x] Graphic, Caption, Username
  * [x] Relative timestamp, like count, user profile image

The following **optional** features are implemented:

* [x] User can **pull-to-refresh** popular stream to get the latest popular photos
* [ ] Show latest comments for each photo
* [ ] Display each photo with the same style and proportions as the real Instagram
* [x] Display each user profile image using a RoundedImageViewDisplay each user profile image using a [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
* [x] Display a nice default placeholder graphic for each image during loading
* [x] Improved the user interface through styling and coloring

The following **bonus** features are implemented:

* [ ] Show last 2 comments for each photo
* [ ] Allow user to view all comments for an image within a separate activity or dialog fragment
* [ ] Allow video posts to be played in full-screen using the VideoView

The following **additional** features are implemented:

* [x] Formatting for the number of likes to include commas
* [x] Pluralized the "likes" string

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='https://github.com/ageiduschek/instagram_client/blob/master/app_walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.
* I had trouble changing the action bar color. In particular, I missed that there are two attributes you have to set (for compatibility reasons): `android:actionBarStyle` and `actionBarStyle`

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [RoundImageView](https://github.com/vinc3m1/RoundedImageView) - Round image view for profile picture
- [PrettyTime](http://www.ocpsoft.org/prettytime/) - Relative time formatting

## License

    Copyright 2015 Anna Geiduschek

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
