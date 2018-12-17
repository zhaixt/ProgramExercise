import org.apache.commons.lang3.StringUtils;

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
        String str = "dskeabcedeh";
        StringUtils.substringBefore(str, "e");
  /*结果是：dsk*/

        StringUtils.substringBeforeLast(str, "e");//一直找到最后一个指定的字符串
  /*结果是：dskeabce*/

        StringUtils.substringAfter(str, "e");
  /*结果是：abcedeh*/

        StringUtils.substringAfterLast(str, "e");
         /*结果是：h*/
    }
}
