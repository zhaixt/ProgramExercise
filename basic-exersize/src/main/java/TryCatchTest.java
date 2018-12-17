/**
 * Created by zhaixiaotong on 2016-5-3.
 */
public class TryCatchTest {
    public static void main(String[] args){
        try{
//            int i = 1/0;
            String s = getNum();
            System.out.println("--resume1---"+s);//函数抛出异常，主程序会继续运行
            String s1 = getNum2();
            System.out.println("--resume2---");//函数抛出异常，主程序会继续运行

        }catch(Exception e){
            System.out.println("==error:==");
            e.printStackTrace();
        }

    }
    public static String getNum() throws Exception{
        Integer i =0;
        try {
            i=  1/0;
            System.out.println("---get Num resume---");
        } catch (Exception e) {
            System.out.println("---error11:---");
            e.printStackTrace();
            return "false";
        }
        System.out.println("get Num ok");
//        i= 1/0;
        return i.toString();
    }

    public static String getNum2() throws Exception{
        Integer i =0;

        i=  1/0;
        System.out.println("===get Num2===");

//        i= 1/0;
        return i.toString();
    }

    public static String getNum3() throws Exception{
        Integer i =0;

        i=  1/0;
        if(true) {
            throw new Exception("throw test");
        }
//        i= 1/0;
        return i.toString();
    }
}
