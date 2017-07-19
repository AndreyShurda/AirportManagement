package com.andrey.main.dl.models;

import com.andrey.main.bl.access.PermissionAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.utils.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.utils.InitialData.PATH_BUNDLES_LOCALE;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    private PermissionAction permissionAction;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PermissionAction getPermissionAction() {
        return permissionAction;
    }

    public void setPermissionAction(PermissionAction permissionAction) {
        this.permissionAction = permissionAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE).getString("user.user") + ": " + getName();
    }
}
