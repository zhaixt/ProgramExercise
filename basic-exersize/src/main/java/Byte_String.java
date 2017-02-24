import java.io.UnsupportedEncodingException;

/**
 * Created by zhaixiaotong on 2016-8-31.
 */
public class Byte_String {
    public static void main(String[] main){
        String a = "hehe";
        try {
            byte[] b = a.getBytes("UTF-8");
            String result = new String(b,"UTF-8");
            System.out.println("result:"+result);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
