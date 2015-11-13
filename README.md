# life.bootstrap
##A simple "Game of Life" Java Servlet bootstrap for the Global Day of Coderetreat

This project will compile a Java 8 .war archive to deploy to an application server. It is a rudimentary servlet that you will be able to reach at <code>http://{my.server.url}/life</code>. 
It has an HTTP header set to refresh the page at every second, each reload representing one generation in the game.
In order to start implementing, all you need to do is use the <code>tick()</code> method to return a String representation of your current generation game board. 
You can use fancy HTML, or output basic ASCII text - anything goes. 

### Logging
The project is configured for SLF4J with logback. You can edit the logback.xml in /src/main/resources to adjust log levels as you like them.

### Testing
I've included dependencies for JUnit and Hamcrest.

### Running with Docker
I have included a simple jetty-based Dockerfile that expects the compile output, <code>life.war</code>, to be present inside the /docker-dir folder. This can, for example, be easily achieved by creating a deployment artifact to run pre-build for use with the [IntelliJ Docker plugin](https://blog.jetbrains.com/idea/2015/03/docker-support-in-intellij-idea-14-1/), or by manually copying the file from the /target folder.  
You can use the docker_settings_template.json to configure the ports on your local docker container as needed.