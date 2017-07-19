package com.andrey.main.bl.access;

import com.andrey.main.dl.dao.utils.InitialData;
import com.andrey.main.dl.models.User;

import java.lang.reflect.Method;

public class PermissionUtils {


    public static boolean processPermission(Class clazz, String methodName) {
        User user = InitialData.CURRENT_USER;
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        MyPermission declaredAnnotation = method.getDeclaredAnnotation(MyPermission.class);
        PermissionAction[] value = declaredAnnotation.value();

        boolean hasPermission = false;
        for (PermissionAction permissionAction : value) {
            if (user.getPermissionAction().equals(permissionAction)) {
                hasPermission = true;
            }
        }

        if (hasPermission) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean processPermission(Class clazz, Command command, String methodName) {
        boolean hasPermission = processPermission(clazz, methodName);
        if (hasPermission) {
            command.execute();
        }
        return hasPermission;
    }

    @FunctionalInterface
    public interface Command {
        void execute();
    }

}
