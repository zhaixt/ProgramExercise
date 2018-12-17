package dynamic_proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * Created by zhaixt on 2018/1/17.
 * 动态代理
 */
public class DynamicProxy {
    public static void main(String args[]){
        Subject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),new Class[]{Subject.class},new ProxyHandler(real));
        proxySubject.doSomething();
        proxySubject.getName(5);
//        createProxyClassFile();
    }

    public static void createProxyClassFile(){
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name,new Class[]{Subject.class});
        try{
            FileOutputStream out = new FileOutputStream(name+".class");
            out.write(data);
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
