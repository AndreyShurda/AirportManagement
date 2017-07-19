package com.andrey.main.dl.models;

import com.andrey.main.bl.access.PermissionAction;


import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.InitialData.PATH_BUNDLES_LOCALE;

public class User extends UserEntity {
    private List<PermissionAction> permissions = new ArrayList<>();

    public User() {
    }

    public User(String name) {
        setName(name);
    }

    public List<PermissionAction> getPermissions() {
        return permissions;
    }


    public void addPermission(PermissionAction permission) {
        List<PermissionAction> permissions = getPermissions();
        permissions.add(permission);
    }


    @Override
    public String toString() {
        return ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE).getString("user.user") + ": " + getName();
    }
}
