#!/bin/bash

# check if maven is available
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed, installing now..."
    # download and install maven
    curl -o apache-maven.tar.gz https://apache.osuosl.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz
    tar -zxvf apache-maven.tar.gz
    rm apache-maven.tar.gz
    export PATH=$PATH:$(pwd)/apache-maven-3.8.4/bin
    echo "Maven has been installed"
fi

# check if the "apps_data" directory exists in the root of the project
if [ -d "apps_data" ]; then
  echo "apps_data directory already exists"
  rm -rf apps_data
  echo "apps_data was successfully removed"
# remove all the previous "apps_data"
else
  # create a directory called "apps_data" in the root of the project
  mkdir apps_data
  echo "create new empty apps_data directory"
fi

# unzip the file to the "apps_data" directory in the root of the project
unzip -o ./config/teamcity.zip -d apps_data
# Start tests
mvn clean install test -T1

# check if allure command is available
if ! command -v allure &> /dev/null; then
    echo "Allure is not installed, installing now..."
    # download and install allure command
    brew install allure
    echo "Allure has been installed"
fi

# start the Allure server
echo "Starting Allure server..."
allure serve target/allure-results