# farmlink-api
## REST API for Project Farmlink

Download and Install
-----------------------------

Java SDK 17

  https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

Postman

  https://www.getpostman.com/downloads

Git

  https://git-scm.com/download/win

IntelliJ IDEA (Community Version will do)

  https://www.jetbrains.com/idea/download

Gradle

  https://gradle.org/releases/

Groovy

  https://groovy.apache.org/download.html

Docker 

  https://www.docker.com/ (note: ensure that Virtualization is enabled in your BIOS. Check accordingly)

Cloning the Project using IntelliJ

1. File > New Project > Project from Version Control > Git

2. Get URL from Github Repo

3. Run build.gradle

  gradle clean build

4. Run FarmlinkApiApplicationGroovy

5. Go to http://localhost:8080/health

To run via Docker

1. ./gradlew build

2. Run these commands via terminal (replace imageName and tag accordingly)

`docker build -t ${imageName}:${tag} . `
`docker run -p 8080:8080 ${imageName}:${tag} `

