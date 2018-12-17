package java8.method_reference2;

/**
 * Created by zhaixt on 2018/11/9.
 */
public class StudentComparator {
    public int compareStudentByScore(Student student1,Student student2){
        return student2.getScore() - student1.getScore();
    }
}
