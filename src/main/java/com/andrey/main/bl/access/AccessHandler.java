package com.andrey.main.bl.access;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.dl.dao.InitialData;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

import static com.andrey.main.bl.access.PermissionUtils.processPermission;
import static com.andrey.main.dl.dao.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.InitialData.PATH_BUNDLES_LOCALE;


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
            String st = InitialData.CURRENT_USER + " " + ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE).getString("accessHandler.msg") +
                    " " + ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE).getString("menu.edit." + method.getName());
            System.out.println(st);
            DialogManager.showInfoDialog(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE).getString("dm.info"), st);
            return null;
        }
    }
}
