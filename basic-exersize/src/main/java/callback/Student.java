package callback;

/**
 * Created by zhaixt on 2018/5/28.
 */
public class Student {
    private String name = null;

    public Student(String name) {
        // TODO Auto-generated constructor stub
        this.name = name;
    }



    public void callHelp(int a, int b) {
        new SuperCalculator().add(a, b, new DoHomeWork());
    }


    public class DoHomeWork implements DoCalculateJob {
        @Override
        public void fillBlank(int a, int b, int result) {
            // TODO Auto-generated method stub
            System.out.println(name + "求助小红计算:" + a + " + " + b + " = " + result);
        }
    }

}
