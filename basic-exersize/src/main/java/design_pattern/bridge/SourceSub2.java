package design_pattern.bridge;

/**
 * Created by zhaixt on 2018/11/20.
 */
public class SourceSub2 implements Sourceable {
    @Override
    public void method() {
        System.out.println("this is the second sub!");
    }
}
