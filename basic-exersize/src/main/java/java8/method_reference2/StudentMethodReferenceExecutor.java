package java8.method_reference2;

import java8.method_reference2.Student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class StudentMethodReferenceExecutor {
    public static void main(String[] args) {

        //1.类名::静态方法名
        System.out.println("1.类名::静态方法名");
        Student student1 = new Student("zhangsan",60);
        Student student2 = new Student("lisi",70);
        Student student3 = new Student("wangwu",80);
        Student student4 = new Student("zhaoliu",90);
        List<Student> students = Arrays.asList(student2,student1,student4,student3);

        students.sort((o1, o2) -> o1.getScore() - o2.getScore());
        students.forEach(student -> System.out.println(student.getScore()));

        //2.对象::实例方法名
        System.out.println("2.对象::实例方法名");

        StudentComparator studentComparator = new StudentComparator();
        students.sort(studentComparator::compareStudentByScore);
        students.forEach(student -> System.out.println(student.getScore()));

        //3.类名::实例方法名
        System.out.println("3.类名::实例方法名");
//        students.sort((o1, o2) -> o1.getScore() - o2.getScore());


        //4.类名::new
        System.out.println("4.类名::new");
        Supplier<Student> supplier = Student::new;
    }
}