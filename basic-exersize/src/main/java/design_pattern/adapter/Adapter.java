package design_pattern.adapter;

/**
 * Created by zhaixt on 2019/1/9.
 */
public class Adapter extends Source implements Targetable {
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
