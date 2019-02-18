package collections;

import java.util.PriorityQueue;
/**
 *  Java的优先队列是小根堆(堆顶的元素为最小元素)，是根据自然排序来进行优先级的判断.
 * 所以自定义的类想要加进优先队列中必须先实现Comparable接口，编写compareTo的方法，方可以使用！
* */
public class PriorityQueueTest {
    private static class Person1 implements Comparable<Person1>{
        public int age;
        Person1(int age){
            this.age=age;
        }
        public int compareTo(Person1 other){

            return age-other.age;

        }
    }

    private static class Person2 implements Comparable<Person2>{
        public int age;
        Person2(int age){
            this.age=age;
        }
        public int compareTo(Person2 other){

            return other.age-age;

        }
    }

    public static void main(String[] args) {
        //根据年龄小的先出优先级
        PriorityQueue<Person1> queue=new PriorityQueue<Person1>();
        Person1 a = new Person1(10);
        Person1 b = new Person1(20);
        queue.offer(a);
        queue.offer(b);
        System.out.println("根据年龄小的先出优先级:");
        System.out.println(queue.poll().age);


        //根据年龄大的先出优先级
        PriorityQueue<Person2> queue2=new PriorityQueue<Person2>();
        Person2 c = new Person2(10);
        Person2 d = new Person2(20);
        queue2.offer(c);
        queue2.offer(d);
        System.out.println("根据年龄大的先出优先级:");
        System.out.println(queue2.poll().age);
    }
}
