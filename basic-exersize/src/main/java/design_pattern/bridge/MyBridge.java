package design_pattern.bridge;

/**
 * Created by zhaixt on 2018/11/20.
 */
public class MyBridge  extends Bridge{
    public void method(){
        getSource().method();
    }
}
