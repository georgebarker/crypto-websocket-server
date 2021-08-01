#!/bin/sh
#mvn clean install
docker build --tag crypto-websocket-server .
docker run -p 80:80 crypto-websocket-server