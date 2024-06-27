package com.github.vladbaton.userservice.resource.pojo;

import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class DeleteUsersRequest {
    private List<Long> userIds;

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUsers(List<Long> userIds) {
        this.userIds = userIds;
    }
}
