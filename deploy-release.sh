#!/bin/bash

. ./version.sh
. ./vars.sh
mvn versions:set -DnewVersion=$VERSION
mvn clean deploy -P release
