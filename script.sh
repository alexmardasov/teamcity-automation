#!/bin/bash

# navigate to the root of the project
cd /path/to/project/

# check if the "apps_data" directory exists in the root of the project
if [ -d "apps_data" ]; then
  echo "apps_data directory already exists"
else
  # create a directory called "apps_data" in the root of the project
  mkdir apps_data
fi

# unzip the file to the "apps_data" directory in the root of the project
unzip -o ./config/teamcity.zip -d apps_data
# Start tests
mvn clean install test