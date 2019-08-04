# Problem 1: Producer-Consumer Problem
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- Gradle 5.5.1 or higher
   - [Download Here](https://gradle.org/releases/)

### Installing

- Open the terminal on Mac or Bash on Windows
- CD to the desired directory
- Give the following commands to initialize a git repository and clone the project

```
git init .
git clone https://github.com/AbelWeldaregay/Operating-Systems-Final-Project.git
```

### Running
- CD to the vmemman/ directory from the project directory cloned from the step above
```
cd prodcons/
```
- Once in the Operating-Systems-Final-Project/vmemman directory, run this command to build and run project
- the 3 represents the number of producers, and 6 represents consumers, they are passed as command line arguments and can be modified
```
gradle build
gradle run --args='3 6'
```
