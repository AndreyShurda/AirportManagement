package com.andrey.main.bl.access;

import com.andrey.main.bl.Utils.DialogManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static com.andrey.main.bl.access.PermissionUtils.processPermission;


public class AccessHandler implements InvocationHandler {

    private Object target;

    private AccessHandler(Object target) {
        this.target = target;
    }

    public static Object newInstance(Object obj) {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new AccessHandler(obj));

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        boolean hasPermission = processPermission(target.getClass(), method.getName());
        if (hasPermission) {
            return method.invoke(target, args);
        } else {
            DialogManager.showInfoDialog(getClass().getSimpleName(), "can't invoke method: \"" + method.getName() + "\" class \"" + target.getClass().getSimpleName()+"\"");
            return null;
        }
    }
}
