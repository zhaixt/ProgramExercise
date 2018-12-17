package callback;

/**
 * Created by zhaixt on 2018/5/28.
 * http://www.importnew.com/19301.html
 */
public class CalculateCallbackTest {
    public static void main(String[] args)
    {
        int a = 56;
        int b = 31;
        int c = 26497;
        int d = 11256;
        Student s1 = new Student("小明");
        Seller s2 = new Seller("老婆婆");

        s1.callHelp(a, b);
        s2.callHelp(c, d);
    }
}
