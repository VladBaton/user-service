package com.github.vladbaton.userservice.resource.pojo;

import com.github.vladbaton.userservice.entity.User;

import java.util.List;

public class UpdateUsersResponse {
    private List<User> updatedUsers;

    public List<User> getUpdatedUsers() {
        return updatedUsers;
    }

    public void setUpdatedUsers(List<User> updatedUsers) {
        this.updatedUsers = updatedUsers;
    }
}
