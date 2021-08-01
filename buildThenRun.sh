#!/bin/sh
#mvn clean install
docker build --tag crypto-websocket-server .
docker run -p 8081:8080 crypto-websocket-server