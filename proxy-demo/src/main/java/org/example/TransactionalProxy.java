package org.example;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransactionalProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Transaction started");
        Object result = null;
        //start time
        try {
            result = proxy.invokeSuper(obj, args);
            // now - start time
            System.out.println("Transaction committed");
        } catch (Exception ex) {
            System.out.println("Transaction rolled back");
            throw ex;
        }
        return result;
    }
}
