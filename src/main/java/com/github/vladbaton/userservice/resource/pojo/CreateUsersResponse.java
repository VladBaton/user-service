package com.github.vladbaton.userservice.resource.pojo;

import com.github.vladbaton.userservice.entity.User;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class CreateUsersResponse {
    private List<User> createdUsers;

    public List<User> getCreatedUsers() {
        return createdUsers;
    }

    public void setCreatedUsers(List<User> notCreatedUsers) {
        this.createdUsers = notCreatedUsers;
    }
}
