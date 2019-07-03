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
    private static final String ZIP_PREFIX = "ZIP:";

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
            //String returnStrNew = new String(ret1,"UTF-8");
            //不用base64加密，gzip.unzip的时候会抛出异常
            String returnStr = Base64.encodeBase64String(ret1);
            System.out.println("字符串base64加密后长度:" + returnStr.length());

            long a = System.currentTimeMillis();
            for (int i = 0; i < 2; i++) {
                boolean isBase = isBase64(returnStr);
            }
            long time = System.currentTimeMillis() - a;
            System.out.println("pattern match judge base64 cost time:" + time);

            //这种快
            long b = System.currentTimeMillis();
            for (int i = 0; i < 20000000; i++) {
                boolean isBase2 = judgeBase64(returnStr);
            }
            long cost2 = System.currentTimeMillis() - b;
            System.out.println("judge base64 cost time:" + cost2);
            boolean isBase3 = judgeBase64(metricsStr);
            metricsStr.startsWith("lala");
            metricsStr.substring(10);


            String prefixReturnStr = ZIP_PREFIX + returnStr;
            long c = System.currentTimeMillis();
            for (int i = 0; i < 20000000; i++) {
                boolean isBase4 = judgeBase64ByPrefix(prefixReturnStr);
            }
            long costPrefix = System.currentTimeMillis() - c;
            System.out.println("prefix judge base64 cost time:" + costPrefix);
            // 还原文件
            byte[] compressedByte = Base64.decodeBase64(returnStr);
            //byte[] compressedByteNew = returnStrNew.getBytes("UTF8");
            byte[] ret2 = GzipUtils.ungzip(compressedByte);
            //byte[] ret2New = GzipUtils.ungzip(compressedByteNew);
            System.out.println("byte数组还原之后大小:" + ret2.length);
            String result = new String(ret2, "UTF-8");
            //String resultNew = new String(ret2New, "UTF-8");
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
    //这种要快一些，但是还是要注意性能，毕竟要遍历整个字符串
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

    private static boolean judgeBase64ByPrefix(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        } else {
            if (str.length() % 4 != 0) {
                return false;
            }
            if (!str.startsWith(ZIP_PREFIX)) {
                return false;
            }
            return true;
        }
    }

}
