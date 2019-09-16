#!/bin/bash

. ./version.sh
. ./vars.sh
mvn versions:set -DnewVersion=$VERSION-SNAPSHOT
mvn clean deploy
