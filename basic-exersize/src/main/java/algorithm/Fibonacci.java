package algorithm;

/**
 * Created by zhaixt on 2019/1/14.
 */
public class Fibonacci {
    public static void main(String[] args) {
        int result = fib(6);
        System.out.println("fab value is:"+result);
        for(int i = 1;i <= 10;i++) {
            //调用函数进行打印
            System.out.print(fib(i) + "\t");
        }

    }
    public static int fib(int n){
        if(n ==1 || n==2) {
            return 1;
        }else {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
