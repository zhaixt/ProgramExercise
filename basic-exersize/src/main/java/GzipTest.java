import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

/**
 * @author: zhaixt
 * @date: 2019/6/24 10:00
 */
public class GzipTest {
    public static void main(String[] args) {
        try {
            // 读取文件
            String metricsStr = "000\n" +
                    "1561346600000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346610000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346620000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|1||\n" +
                    "1561346630000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346640000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346650000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346660000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346670000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346680000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346690000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346700000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n" +
                    "1561346710000|pg:rateLimitEventService_1.0.0&getMetricsEventInfo|7|0|7|0|0||\n";
            System.out.println("字符串原始大小:" + metricsStr.length());
            byte[] data = metricsStr.getBytes("UTF8");

            System.out.println("字符串转为byte数组大小:" + data.length);


            // 测试压缩
            byte[] ret1 = GzipUtils.gzip(data);
            System.out.println("byte数组压缩之后大小:" + ret1.length);
            String returnStr = Base64.encodeBase64String(ret1);
            System.out.println("字符串base64加密后长度:" + returnStr.length());

            long a = System.currentTimeMillis();
            boolean isBase = isBase64(returnStr);
            long b = System.currentTimeMillis();
            //这种快
            boolean isBase2 = judgeBase64(returnStr);
            long c = System.currentTimeMillis();

            // 还原文件
            byte[] compressedByte = Base64.decodeBase64(returnStr);
            byte[] ret2 = GzipUtils.ungzip(compressedByte);
            System.out.println("byte数组还原之后大小:" + ret2.length);
            String result = new String(ret2, "UTF-8");
            System.out.println("字符串恢复后大小:" + result.length());
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static boolean isBase64(String str) {
        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Pattern, str);
    }

    private static boolean judgeBase64(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        } else {
            if (str.length() % 4 != 0) {
                return false;
            }

            char[] strChars = str.toCharArray();
            for (char c : strChars) {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
                        || c == '+' || c == '/' || c == '=') {
                    continue;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

}
