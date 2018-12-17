package dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhaixt on 2018/1/17.
 */
public class ProxyHandler implements InvocationHandler {
    private Object proxied;

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;//被代理类，即真实的类
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
//        return method.invoke(proxied,args);

        System.out.println("++++++before " + method.getName() + "++++++");
        Object result = method.invoke(proxied, args);
        System.out.println("++++++after " + method.getName() + "++++++");
        return result;
    }

}
