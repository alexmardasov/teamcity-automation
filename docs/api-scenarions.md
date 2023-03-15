# API Scenarions:

---

## API - Create a new user

### Preconditions:

At least one user exists on system and has Bearer token to invoke API methods.

### Steps:

1. Prepare json payload to create new user, e.g.:

````
{
    "username": "username",
    "password": "password",
    "email": "email",
    "name": "name"
}
````

2. Post User entity to `` POST /app/rest/users``

### Result

1. Status code 200
2. User is successfully created
3. Response body contains all the data from request:

````
{
    "username": "username_from_request",
    "name": "name_from_request",
    "id": "not_null_generated_automatically",
    "email": "emai_from_request",
    "hasPassword": "true",
    ...
}
````
---
## API - Create a new project

### Preconditions:

At least one user exists on system and has Bearer token to invoke API methods.

### Steps:

1. Prepare json payload to create a new project, e.g.:
2. Specify name, id, and parentProject via its locator string (to define a project under the Root project, use id:_Root).

````
{
    "parentProject": {
        "locator": "id:_Root"
    },
    "name": "name",
    "copyAllAssociatedSettings": true,
    "sourceProject": {
        "locator": "locator"
    }
}
````

3. Post Project entity to `` POST /app/rest/projects
   ``

### Result

1. Status code 200
2. Project is successfully created
3. Response body contains all data from request:

````
{
   "id": "not_null_generated_automatically",
   "name": "name_from_request",
   "parentProjectId": "_Root",
   ...
}
````
---
## API - Create VCS root

### Preconditions:

At least one user exists on system and has Bearer token to invoke API methods.

### Steps:

1. Prepare json payload to create new vcs root, e.g.:
````
{
    "id": "MyCustomRoot",
    "name": "MyCustomRoot",
    "vcsName": "jetbrains.git",
    "project": {
        "id": "MyCustomProject"
    },
    "properties": {
        "property": [
            {
                "name": "authMethod",
                "value": "ANONYMOUS"
            },
            {
                "name": "branch",
                "value": "refs/heads/master"
            },
            {
                "name": "url",
                "value": "https://github.com/alexmardasov/test-rep.git"
            }
        ]
    }
}
````
2. Post VCS entity to `` POST /app/rest/vcs-roots``

### Result

1. Status code 200
2. VCS is successfully created
3. In response:
````
{
    "id": "prjectId_name",
    "name": "MyCustomRoot",
    "vcsName": "jetbrains.git",
    ...
}
````
---

## API - Create new build configuration

### Preconditions:

1. At least one user exists on system and has Bearer token to invoke API methods
2. At least one project exists on system.

### Steps:

1. Prepare json payload to create a new build configuration, e.g.:
````
{
  "id": "id",
  "name": "name",
  "project": {
    "id": "MyProjectID"
  },
  "parameters": {
    "property": [
      {
        "name": "myBuildParameter",
        "value": "myValue"
      }
    ]
  }
}
````
2. BuildType entity to `` POST /app/rest/buildTypes``

### Result

1. Status code 200
2. Build configuration is successfully created
3. In response:
````
{
    "name": "name_from_request",
    "projectId": "project_id_from_request",
    "parameters: "parameters_from_request
    ...
}
````
---
## API - Run build

### Preconditions:

1. At least one user exists on system and has Bearer token to invoke API methods
2. At least one project having some build configuration and vcs connection exists on system.


### Steps:

1. Prepare json payload to run build configuration, e.g.:
````
{
	"buildType": {
    	"id": "buildConfID"
	}
}
````
2. Add this BuildType entity to the queue with ``POST /app/rest/buildQueue``

### Result

1. Status code 200
2. Build configuration is added to the queue.
3. In response:
````
{
    "id": "buildConfID",
    "state": "queued or success",
    ...
}
````