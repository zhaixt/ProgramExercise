package java8.method_reference;

/**
 * Created by zhaixt on 2018/10/24.
 */
public class MethodReferenceExecutor {
    public static void main(String[] args) {
        execute("xiaoming",message -> {
            System.out.println(Thread.currentThread().getId()+":hey: " + message);
        });
    }

    public static  void  execute(final String msg,final GreetingService greetingService){
        System.out.println(Thread.currentThread().getId()+":say hello:"+msg);
        greetingService.sayHello("lala");
    }
}
