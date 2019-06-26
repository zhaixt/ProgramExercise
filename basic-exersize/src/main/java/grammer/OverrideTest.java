package grammer;

/**
 * @author: zhaixt
 * @date: 2019/6/25 16:33
 */
public class OverrideTest {
    public static void doSth() {
        OverridedTask overridedTask = new OverridedTask() {
            @Override
            public void run(){
                System.out.println("lalala");
            }
        };
        OverridedTask originOverridedTask = new OverridedTask() ;
        overridedTask.run();
        originOverridedTask.run();
        System.out.println("over!");
    }

    public static void main(String[] args) {
        doSth();;
    }
}
