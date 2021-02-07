
| Branch        | Build           | Coverage           |
| ------------- |:-------------:|:-------------:|
| master      | [![Build Status](https://travis-ci.org/patrykkrawczyk/TDDAndDesignPatternsExample.svg?branch=master)](https://travis-ci.org/patrykkrawczyk/TDDAndDesignPatternsExample) | [![codecov](https://codecov.io/gh/patrykkrawczyk/TDDAndDesignPatternsExample/branch/master/graph/badge.svg)](https://codecov.io/gh/patrykkrawczyk/TDDAndDesignPatternsExample/branch/master) |


Social Networking Kata
----------------------
My solution to the [exercise](https://github.com/xpeppers/social_networking_kata) used by [XPeppers](https://www.xpeppers.com/) for their recruitment process.

### Requisites

* [Maven 3.6+](https://maven.apache.org/download.cgi)
* [Java 11](https://www.oracle.com/it/java/technologies/javase-jdk11-downloads.html)

### How to compile

> mvn clean package

If you don't want to run tests
> mvn clean package -Dmaven.test.skip=true

### How to run

> java -jar target/app.jar

you can download the latest release if you don't want to compile the project.

## Code explanation

This morning I spilled coffee on myself, therefore I choose to solve the exercise in Java.
First of all, I tried to find a simple way to specify the acceptance tests proposed by the exercise specifications.
Immediately I faced the need to simulate the system console in the test environment, so I created the [ICommandLineInterface] abstraction.
After writing most of the acceptance tests and making them work, I noticed that I wrote a lot of code smells, so I started a refactoring that brought me up to three macro-components:

* A user interface reading and writing text lines (ICommandLineInterface) 
* A parser whose responsibility is to create an executable command given a text line. (ICommandParser)
* A proxy towards a social network, which has its own domain. (ISocialNetwork)

The application starts in the App class.

### Improvements

* In the CommandParser, I didn't want to implement the usual factory with a lot of if/else, so I tried to introduce a ICommandRegistry interface. The aim was to find a more flexible solution so that the command configurations could be injected into the parser from an external source. The overall architecture could be summarized through the following image:

![](kata.png)

* I added the Help and Exit commands. They came for free. In particular, I suddenly realized that the Help command could print the list of available commands coming from the ICommandRegistry. Cool!

* In order to print the `n <chrono unit> ago` I wanted to use PrettyTime, but in the end I fell back on a home-made solution. 

### Demo

![](demo.gif)

