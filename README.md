# Teamcity automation framework concept

This is an automation testing framework concept for testing TeamCity API and UI using Java 11, Maven, Docker, Testcontainers, JUnit, RestAssured, Lombok, Hamcrest Matchers and Allure report.

# Getting started:

To get started with the framework, you will need to have the following installed and configured on your machine:
 - Java 11 (I use Amazon corretto JDK)
 - Docker
 - Maven

# Installation:

To install the project to your machine you need to clone the project first:

````
git clone https://github.com/alexmardasov/teamcity-automation.git
````

# Usage:

To simply run tests, follow these steps:

1. Navigate to the root directory of the project.
2. Run the script using the following command:
````
./run-tests.sh
````

This will perform the following actions:
- Check if the "apps_data" directory exists in the root of the project. If it does, the script will remove it and all previous "apps_data". If it doesn't, it will create a new empty directory.
- Unzip the file teamcity.zip to the apps_data directory.
- Run the tests using Maven.
- Check if Allure is installed. If it's not, it will install it using Homebrew.
- Start the Allure server and open the Allure report in your default web browser.

You can pass the argument `--browser` to the script to start the container with selected browser for tests, e.g. ``./run-tests.sh --browser=FIREFOX``
(Note: I didn't debug tests for both browsers, so in Firefox they can be unstable. This was added to show the additional feature of the framework)

# How it works:

1. When script has launched it unzips data from "teamcity.zip" to apps_data folder. This is just an initial configuration of Teamcity.
I decided to add it to avoid some additional steps when systems is starting, like start from backup, create a new database (It is not main scenarios and can be tested later).
It includes created internal database, user with credentials = {name:admin, password:test} and generated Bearer token for the user.

2. When tests are compiling, Hamcrest matchers are automatically generated for all data classes (pojos). I use https://github.com/marmer/hamcrest-matcher-generator
library for it.

3. When first test is launched, it starts Teamcity and Agent containers from ./docker/teamcity-compose.yml. I wait until "TeamCity is running" in logs, that means teamcity server has been 
initialized and launched, after that I'm trying to authorize all the agents. Only after that a test itself is going to be run.

4. UI tests are launched a container with Browser and perform all the steps inside it. If some test will fail a new recording with its execution will be added to ./logs/vnc-records. By default it records
a video only when test fails, but you can switch recording mode to record video for passed tests in Settings.class or by passing framework.vnc.mode=RECORD_ALL property to maven.

5. When all tests have finished their execution, Teamcity containers will be stopped. Testcontainers will do it for you.

# What can be improved:
- I disable CSRF protection https://www.jetbrains.com/help/teamcity/csrf-protection.html when start Teamcity server. It may be not secure but in terms of the task I suppose it's okay. Having this feature enabled adds additional complexity to making requests to API.
- Think about how to run tests concurrently, and how to configure data for tests so that they do not interfere with each other. By now they run in one thread and I use the same user among the tests. I think this can be achieved by creating new users for each test with some special permissions (e.g. such users can see only projects and builds that they create).
- Extend the number of fields that I use in payloads for requests and in asserts in responses. By far I use mostly necessary fields.
- Extend Allure report with steps and output of test result