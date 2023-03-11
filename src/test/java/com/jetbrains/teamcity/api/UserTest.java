package com.jetbrains.teamcity.api;

import com.jetbrains.teamcity.services.pojos.user.CreateNewUserRequest;
import com.jetbrains.teamcity.services.pojos.user.CreateNewUserResponseMatcher;
import com.jetbrains.teamcity.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;

public class UserTest {

    @Test
    @DisplayName("Teamcity API - Create new User")
    public void testCreateNewUser() {
        var userRequest = CreateNewUserRequest.createUserWithBasicParameters();
        var createNewUserResponse = UserService.createNewUser(userRequest);

        var createNewUserResponseMatcher = new CreateNewUserResponseMatcher()
                .withName(userRequest.getName())
                .withUsername(userRequest.getUsername())
                .withEmail(userRequest.getEmail());

        assertThat(createNewUserResponse, createNewUserResponseMatcher);
    }
}
