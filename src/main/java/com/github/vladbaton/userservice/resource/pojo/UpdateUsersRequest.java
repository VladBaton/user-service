package com.github.vladbaton.userservice.resource.pojo;

import com.github.vladbaton.userservice.entity.User;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class UpdateUsersRequest {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
