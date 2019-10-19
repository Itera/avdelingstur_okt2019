#!/usr/bin/env bash

mvn clean package
cp target/rest-api-1.0-SNAPSHOT.jar dist/rest-api-1.0-SNAPSHOT.jar
docker build . -t rest-api-docker:local
