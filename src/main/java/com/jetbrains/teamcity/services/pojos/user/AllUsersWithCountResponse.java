package com.jetbrains.teamcity.services.pojos.user;

import java.util.List;

public class AllUsersWithCountResponse {
    private int count;
    private List<UserItem> user;
    public int getCount() {
        return count;
    }

    public List<UserItem> getUser() {
        return user;
    }
}