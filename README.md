# life.bootstrap
##A simple "Game of Life" NodeJS bootstrap for the Global Day of Coderetreat

This project will run a simple express app at <code>http://{my.server.url}/life</code>. 
It has an HTTP header set to refresh the page at every second, each reload representing one generation in the game.
In order to start implementing, all you need to do is use the <code>tick()</code> method to return a String representation of your current generation game board. 
You can use fancy HTML, or output basic ASCII text - anything goes. 

### Testing
I've included dependencies for Supertest, Mocha, Istanbul and Sinon.

### Running with Docker
I have included a simple Node 6 Dockerfile that you can mount the node app into. 
For convenience, there is a run-docker.sh script that you can simply execute, or use as a blueprint for building and running the image.