package com.github.vladbaton.userservice.resource.pojo;

import com.github.vladbaton.userservice.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

import java.util.List;

@RequestScoped
public class DeleteUsersResponse {
    private List<Long> deletedUserIds;

    public List<Long> getDeletedUsers() {
        return deletedUserIds;
    }

    public void setDeletedUsers(List<Long> notDeletedUserIds) {
        this.deletedUserIds = notDeletedUserIds;
    }
}
