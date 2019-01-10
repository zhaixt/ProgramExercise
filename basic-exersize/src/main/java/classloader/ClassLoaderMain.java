package classloader;

/**
 * Created by zhaixt on 2018/12/21.
 */
public class ClassLoaderMain {
    public static void main(String[] args) {
        ClassLoader  loader = ClassLoaderMain.class.getClassLoader();
        while(loader != null){
            System.out.println(loader);
            loader = loader.getParent();
        }
        System.out.println(loader);


        try {
            /**
             * 1:initialize为true，直接运行static区块，这里和下面要分别注释，因为只加载一次
             * */
            System.out.println("载入 StaticTestClass 1");
            Class c1 = Class.forName("classloader.StaticTestClass");
            Object obj = c1.newInstance();
            /**
             * 2: initialize为false,懒加载
             * param3 classloader,这里为主线程类加载器
             * */
            System.out.println("载入 StaticTestClass 2 ");

            Class c2 = Class.forName("classloader.StaticTestClass",false,Thread.currentThread().getContextClassLoader());
            System.out.println("使用 StaticTestClass 2 建立对象");
            StaticTestClass test = new StaticTestClass();

            /**
             * 3: 使用classloader
             * 这里不会运行静态区代码段
             * */
            System.out.println("使用 classloader 载入 StaticTestClass 3 ");
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class<?> c3 = classLoader.loadClass("classloader.StaticTestClass");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    protected  void testClassloader(String wholeNameLine, String wholeNamePoint) {
        ClassLoader classloader = ClassLoader.getSystemClassLoader();
//        classloader.loadClass()
        try {
            Class<?> clazz = Class.forName("classloader.Benz");
            //initialize是会不会立即运行静态区块，而会在使用类建立对象时才运行静态区块。
            Class<?> clazz2 = Class.forName("classloader.Benz",false,classloader);
//            Class<?> loadClass = classloader.loadClass("classloader.Benz", false);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
