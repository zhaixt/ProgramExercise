/**
 * Created by zhaixiaotong on 2016-4-28.
 */
public class EnumTest {
    public enum Color{Red,Yellow,Green,Blue,Black,White}
    public static void main(String[] args)
    {
        String a = "Green";
        if("Green".equals(Color.Green)) {
            System.out.println(Color.Black);//false
        }
    }
}
