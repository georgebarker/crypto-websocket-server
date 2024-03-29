# Cryptocurrency WebSocket Server
A Java application running on Tomcat that pushes the prices for cryptocurrencies to clients listening on a [WebSocket](https://developer.mozilla.org/en-US/docs/Web/API/WebSocket).

## How to use
1. The script `buildThenRun.sh` will build the Java, create a Docker image and then run a Docker container for you. 
2. A client will be required; to get up and running quickly you can use a browser extension such as [Simple WebSocket Client](https://addons.mozilla.org/en-US/firefox/addon/simple-websocket-client/).
3. Using the client, open a connection to the URL `ws://localhost:8081/crypto-websocket-server/cryptocurrencies`. 
4. Your client will begin to receive prices.

## Things to improve
- [X] Introduce Log4j
- [X] Ensure logged objects are not simply logging their HashCode
- [X] Make the random data a bit more random
- [X] Introduce a tick size field on the Cryptocurrency model
- [X] Create a `run.sh` script
- [X] Introduce dependency injection
- [X] Connect to Binance API
- [ ] Make it possible to debug the docker container
- [ ] Unit tests
- [ ] Decouple the Tomcat service name from the WAR name so that the WAR can be deployed with its version number, but be invoked without it
- [ ] Give the Docker container a nice hostname like `crypto-websocket-server.docker`
- [ ] Find a nicer way of logging 3rd party objects than calling the LogHelper everytime, perhaps this can be achieved with a pointcut
- [ ] Create a diagram that shows how this application works
- [ ] Swagger API documentation, need to find out how to document a WebSocket API
- [ ] Parameterise the `buildThenRun.sh` script to allow for running the application on any port specified
- [ ] Modify the `buildThenRun.sh` script to tidy up stale images
- [ ] Provide the WebSocket over wss (WebSocket Secure)
- [ ] Decide whether to push Arrays of Cryptocurrencies or single Cryptocurrencies; tidy up code to reflect decision
- [ ] Implement checkstyle

## Sample clients
1. I have built a sample client on iOS, [ios-crypto-websocket-client](https://github.com/georgebarker/ios-crypto-websocket-client).
2. As mentioned, [Simple WebSocket Client](https://addons.mozilla.org/en-US/firefox/addon/simple-websocket-client/) browser extension gets you up and running quickly.
