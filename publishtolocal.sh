#!/bin/bash

echo pwd

./gradlew clean
./gradlew publishToMavenLocal
