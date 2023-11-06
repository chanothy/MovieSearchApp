# Movie Search App

App that uses OMDb API to search and display information about movies.

## Functionality 

The following **required** functionality is completed:

* [ ] User can search for movies.
* [ ] Uses retrofit to send request for movie information.
* [ ] Parses recieved information.
* [ ] Displays movie information.
* [ ] User is able to share movie.
* [ ] User is able to send feedback.
* [ ] User is able to go to IMDb page.

The following **extensions** are implemented:

* Retrofit
* viewBinding
* Glide
* GSON

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/chanothy/MovieSearchApp/blob/master/movieSearchAppDemo.gif' title='Video Walkthrough' width='50%' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

It wasn't easy getting the intents to work because I needed a context. It was also confusing how to pare the JSON data because I initially did valid calls, but I didn't parse the data correctly with a correct model.

## License

    Copyright [2023] [Timothy Chan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
