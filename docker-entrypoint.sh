#!/usr/bin/env bash

set -e
WORKSPACE=/opt/sbom-repo-service
cd ${WORKSPACE}

git pull

/bin/bash gradlew bootWar

java -jar ${WORKSPACE}/build/libs/sbom-repo-service-1.0-SNAPSHOT.war --spring.profiles.active=prod