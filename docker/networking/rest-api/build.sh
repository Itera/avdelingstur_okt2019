#!/usr/bin/env bash

mvn clean package
docker build . -t rest-api-docker:local
