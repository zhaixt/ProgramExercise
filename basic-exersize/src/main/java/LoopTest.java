import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaixiaotong on 2016-5-3.
 */
public class LoopTest {
    public static void main(String[] args) {
        String[] strArray = new String[] {"a2", "b2", "c2","d2"};
        List<String> stringList = Arrays.asList(strArray);
        boolean isStr = false;
        isStr = judgeContainsStr(stringList,"bb2");
        System.out.println("is true:"+isStr);
        for(String str:stringList){
            System.out.println("now execute:"+str);
            if(str.equals("c2")){
                isStr = true;
                return ;
            }
        }
        System.out.println("another is true:"+isStr);

    }
    private static boolean judgeContainsStr(List<String> stringList,String equalStr){
        boolean isStr = false;
        for(String str:stringList){
            System.out.println("now in function execute:"+str);
            if(str.equals(equalStr)){
                isStr = true;
                return true;
            }
        }
        return isStr;
    }
}
