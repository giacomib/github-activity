# github-activity
A simple command line interface (CLI) to fetch the recent activity of a GitHub user and display it in the terminal.

## Project URL
[https://roadmap.sh/projects/task-tracker](https://roadmap.sh/projects/github-user-activity)

# Documentation

## How to run the app
- to run the app from the command line, move to the github-activity/src folder, then run the command `javac App.java` to compile the code, then, to run the app, you have to run the command `java App ...` where the `...` stand for the username you want the app retrieve informations about, example: i want to know information about the user giacomib, i will run the following command: `java App giacomib`

## Assumptions
This app works by translating the result of the github API (that is a JSON file) into a list of events (a class that i've created to better handle all events); this translation is made using the multiple instances of the characters '{' and '}', that form the base structure of a JSON file; if any content inside the retrieve information contains the characters '{' or '}', the app will probably brake.

## Tests made
- [x] run the app with an empty username
- [x] run the app with a username that does not exist
- [x] run the app with a valid username
