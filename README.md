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

Gradle 8.1

  https://gradle.org/releases/

Groovy 4.0.2

  https://groovy.apache.org/download.html

Docker 

  https://www.docker.com/ (note: ensure that Virtualization is enabled in your BIOS. Check accordingly)

Cloning the Project using IntelliJ

1. File > New Project > Project from Version Control > Git

2. Get URL from Github Repo

3. Run build.gradle (do this step if you're running via Docker otherwise, proceed to step 4)

  gradle clean build

4. Run FarmlinkApiApplicationGroovy (this is assuming that your IntelliJ has the Spring plugins ready)

5. Go to http://localhost:8080 and expect a JSON message

6. Use http://localhost:8080/health as a health check

To run via Docker

1. gradle clean build

2. Run these commands via terminal (replace imageName and tag accordingly)

`docker build -t ${imageName}:${tag} . `
`docker run -p 8080:8080 ${imageName}:${tag} `

