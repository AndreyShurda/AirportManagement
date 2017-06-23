package com.andrey.main.bl.access;

import java.util.ArrayList;
import java.util.List;


public class User {
    private String name;
    private List<PermissionAction> permissions = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void addPermission(PermissionAction permission) {
        List<PermissionAction> permissions = getPermissions();
        permissions.add(permission);
    }

    public List<PermissionAction> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "User: " + name;
    }

    public String getName() {
        return name;
    }
}
