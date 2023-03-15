===================================================================================

### [TC-1] Login Page - Check login with correct credentials
    
    Priority: High
    Severity: Critical
    Estimate: 5m
    Component: Teamcity UI

### Actions
1. Open TeamCity login page.
2. Set correct username.
3. Set correct password.
4. Press 'Login' button.

### Result 
1. User is logged in.
2. Main page of the application is opened.

===================================================================================
### [TC-2] Login Page - Check login with incorrect credentials

    Priority: Low
    Severity: Trivial
    Estimate: 10m
    Component: Teamcity UI

### Actions
1. Open TeamCity login page.
2. Set incorrect username.
3. Set correct password.
4. Press 'Login' button.

### Result
1. User is not logged in.
2. Error message appears below the input fields.

### Options
1. Check for correct username and incorrect password.

===================================================================================
### [TC-3] Login Page - Check 'Remember me' feature

    Priority: Medium
    Severity: Minor
    Estimate: 15m
    Component: Teamcity UI

### Actions
1. Open TeamCity login page.
2. Set correct username.
3. Set correct password.
4. Mark 'Remember me' checkbox.
5. Press 'Login' button.
6. Open DevTools -> Application -> Cookies and check Cookies for the teamcity url. 
7. Close and open browser with teamcity login page.
8. Open DevTools -> Application -> Cookies and clear Cookies.
9. Close and open browser with teamcity login page.

### Result
1. After p.6 Cookie with name 'RememberMe' is added.  
2. After p.7 Login page is not opened. User is automatically logged in and redirected to the main page.
3. After p.9 Login page is opened.

===================================================================================
### [TC-4] Login Page - Check "Reset password" feature

    Priority: Medium
    Severity: Major
    Estimate: 10m
    Component: Teamcity UI

### Actions
1. Open TeamCity login page.
2. Click on 'Reset password' link.
3. In opened 'Reset password' page input email and press 'Next' button.

### Result
1. 'An email with further instructions will be sent to 'userEmail' if a user with the specified email address exists.' message apperas.
2. Email with a manual how to reset the password is sent to the user.

### Options
1. Check that user can return to login page by clicking 'Login Page' link under the message from result.

===================================================================================
### [TC-5] Login Page - Check Login page default view

    Priority: Low
    Severity: Minor
    Estimate: 10m
    Component: Teamcity UI

### Actions
1. Open TeamCity login page.
2. Check the page default view.


### Result
1. Login page is opened and contains:
- TeamCity logo
- Log in to TeamCity label
- input field with Username label
- input field with Password label
- 'Remember me' checkbox
- Reset password link
- 'Log in' button
- label with version of the applicaiton


### Link
1. Here must be a link to designers spec.





