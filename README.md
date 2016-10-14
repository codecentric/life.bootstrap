# life.bootstrap
##A simple "Game of Life" Scala/Akka bootstrap for the Global Day of Coderetreat

This project will compile a Java 8 .jar archive to deploy to docker, or run locally. It is an akka-http Service running at localhost:8080.
It has an HTTP header set to refresh the page at every second, each reload representing one generation in the game.
In order to start implementing, all you need to do is use the <code>tick()</code> method to return a String representation of your current generation game board. 
You can use fancy HTML, or output basic ASCII text - anything goes. 

### Testing
I've included dependencies for Scalatest.

### Running with Docker
You can build a docker image with <code>sbt docker</code>, and run it with <code>docker run --name life -p 8080:8080 de.codecentric/life-bootstrap:latest</code>