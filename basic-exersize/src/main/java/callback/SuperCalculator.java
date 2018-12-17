package callback;

/**
 * Created by zhaixt on 2018/5/28.
 */
public class SuperCalculator
{
    public void add(int a, int b, DoCalculateJob  customer)
    {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
}