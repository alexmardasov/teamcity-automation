# UI Scenarios:

---

## UI - Login with a new user

### Preconditions:

1. Teamcity server is launched.

### Steps:

1. Create a new user from API using `POST /app/rest/users` method.
2. Try to login to with the user to Teamcity UI.

### Result

1. User is successfully created.
2. Main page is opened. Teamcity dashboard is available.

---
## UI - Create a new project and build configuration from repository URL

### Preconditions:

1. Teamcity server is launched.
2. User is logged in.
3. Some public repository is selected that will be used in the test.

### Steps:

1. Press 'Create new project' button.
2. In opened form fill all the necessary fields: 
- Repository URL: https://github.com/alexmardasov/test-rep.git 
- Username: username (optional)
- Password / Access token: some token (optional)
3. Press 'Proceed' button.
4. In opened form fill all the necessary fields:
- Project name: desired project name
- Build configuration name: desired build configuration name
- Default branch: the main branch to be monitored
- Branch specification: some branch specification
5. Press 'Proceed' button.

### Result

1. After p.3 "The connection to the VCS repository has been verified" appears.
2. After p.5 Project and build configuration is created.
3. The message 'New project "project_name", build configuration "build_name" and VCS root "https://github.com/alexmardasov/test-rep.git#refs/heads/main" have been successfully created.' appears.

---

## UI - Run build with steps and producing some artifacts

### Preconditions:

1. Teamcity server is launched.
2. Some project exists on system with build configuration that:
- have some basic steps, e.g: simple script that do echo 'Hello World!'
- have settings with property: name = artifactRules, value = README.md
3. VCS connection of build configuration have url value to repository with README.md at the root directory.
4. User is logged to Teamcity UI.

### Steps:

1. Find the build from preconditions at the left sidebar.
2. At right top corner press 'Run..' button.
3. Wait until the build will finish its execution.
4. Check build overview
5. Check build log for the presence of the script execution.
6. Check build artifacts. 

### Result

1. Build finished its execution and has 'Success' status.
2. Step is executed and appears in build log with output: 'Hello World!' 
3. Build Artifacts contain README.md file from the repository.