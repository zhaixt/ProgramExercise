import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhaixiaotong on 2017-5-11.
 */
public class ObjectTest {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1==str2);
        System.out.println(str1.equals(str2));
        System.out.println(str1.hashCode()==str2.hashCode());

        String strA = new String("abc");
        String strB = new String("abc");
        System.out.println(strA==strB);
        System.out.println(strA.equals(strB));
        System.out.println(strA.hashCode()==strB.hashCode());


        String nullStr = "a";
        boolean isNull =  nullStr.equals(null);
        System.out.println("is Null?"+isNull);
        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("c");
        list.add("b");
        Collections.sort(list);//升序排列
        Collections.reverse(list);//降序排列
        List<String> list2 = new ArrayList<>();
        list2.add("c");

        //list和list2合并，并且不能有重复
        list.removeAll(list2);
        list.addAll(list2);

        String a = "301226.41";
        String b = "-122345.24";
        Float c = Float.parseFloat(a)+Float.parseFloat(b);

        if(c>=500000&&c<=10000000){
            System.out.println("c ok:"+c);
        } else{
            System.out.println("c failed:"+c);

        }
        int chu = 30/20;
        System.out.println(chu);
        Student student = new Student();
        student.setAge(student.getHeight());

        Student stu1 = new Student();
        Student stu2 = new Student();

    }



    public static class Student{
        private int age;
        private int height;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
