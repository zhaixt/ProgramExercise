package design_pattern;

/**
 * Created by zhaixt on 2018/10/21.
 */
public class Singleton {
    //volatile 是为了防止jvm乱序写入
    // 即指令重排序，使内存分配、初始化、返回对象在堆上的引用这三步可能是1、2、3，也可能是1、3、2
    private volatile static Singleton singleton;
    public Singleton(){}
    public static Singleton getInstance(){
        //双重检测
        if( null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
