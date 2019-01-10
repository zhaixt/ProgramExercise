package classloader;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhaixt on 2018/12/21.
 */
@AllArgsConstructor
@NoArgsConstructor
public class MyClassLoader extends ClassLoader {
    private String path; //类的加载路径
    private String classLoaderName; //类加载器的名字

    public Class findClass(String className) {
        byte[] b = loadClassData(className);
        return defineClass("classloader.Benz",b,0,b.length);//这里需要类所在的package名:package classloader;
    }

    private byte[] loadClassData(String className) {
        String fullName = path + className + ".class";

        InputStream in = null;


        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(fullName));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }

        } catch (Exception e) {
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }

    public static void main(String[] args) {
        /*
        * 从外部加载class
        * */
        MyClassLoader myClassLoader = new MyClassLoader("D:\\ProgrammingStudy\\ProgramExercise\\basic-exersize\\target\\classes\\classloader\\","classLoader1");
        Class<?> clazz = myClassLoader.findClass("Benz");

        try {
            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("drive");
            method.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.print(clazz.getClassLoader());

        testClassloader();
    }

    /**
     *  classloader
    * */
    private static void testClassloader(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("\n===== Test class loader=====");
        try {
            Class<?> benz = classLoader.loadClass("classloader.Benz");
            System.out.println("benz:   " +benz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
