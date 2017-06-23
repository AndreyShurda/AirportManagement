package com.andrey.main.bl.access;

import com.andrey.main.dl.dao.InitialData;

import java.lang.reflect.Method;

public class PermissionUtils {


    public static boolean processPermission(Class clazz, String methodName) {
        User user = InitialData.CURRENT_USER;
//        Method method = clazz.getDeclaredMethod(methodName, new Class<?>[]{User.class});
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        MyPermission declaredAnnotation = method.getDeclaredAnnotation(MyPermission.class);
        PermissionAction[] value = declaredAnnotation.value();
//        PermissionAction value = declaredAnnotation.value();

        boolean hasPermission = false;
        for (PermissionAction permissionAction : value) {
            if (user.getPermissions().contains(permissionAction)) {
//                if (user.getPermissions().contains(value)) {
                hasPermission = true;
            }
        }

        if (hasPermission) {
            System.out.println("User " + user + " successfully " + methodName);
            return true;
        } else {
            System.out.println("User " + user + " can`t " + methodName);
            return false;
        }
    }

    public static boolean processPermission(Class clazz, Command command, String methodName) {
        boolean hasPermission = processPermission(clazz, methodName);
        if (hasPermission) {
            command.execute();
            System.out.println("execute command");
        }
        return hasPermission;
    }

    @FunctionalInterface
    public interface Command {
        void execute();
    }

}
