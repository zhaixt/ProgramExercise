package collections;

import java.util.Stack;

/**
 * @author zhaixt
 * @date 2020/12/3 11:20
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        /*push是Stack自身的方法，返回值类型是参数类类型。*/
        String s = stack.push("aa");
        stack.push("bb");
        /*add是继承自Vector的方法，且返回值类型是boolean。*/
        stack.add("cc");
        /*peak不弹出栈顶元素*/
        System.out.println(stack.peek());
        System.out.println("stack:" + stack.toString());
        System.out.println(stack.pop());
        /*pop弹出栈顶元素*/
        System.out.println("stack2:" + stack.toString());

    }
}
