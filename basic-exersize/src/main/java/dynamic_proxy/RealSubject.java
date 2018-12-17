package dynamic_proxy;

/**
 * Created by zhaixt on 2018/1/17.
 */
public class RealSubject implements Subject{
    public void doSomething(){
        System.out.println( "call doSomething()" );
    }
    public String getName(int id){
        System.out.println("------getName------");
        return "zhaixt";
    }

}
