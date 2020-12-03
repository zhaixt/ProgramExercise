import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhaixt on 2018/4/20.
 */
public class NumberTest {
    public static void main(String[] args) {
//        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int a = 12;
        Integer b = 10;
        Double c = 10.2;
        String str = b.toString();
        String str2 = c.toString();
        BigDecimal d1 = new BigDecimal(a);
        BigDecimal d2 = new BigDecimal(str);
        BigDecimal d3 = new BigDecimal(str2);

        Map<String, Long> map = new HashMap<>();
        map.put("string", null);

        ConcurrentHashMap<String, Long> appResourceConfigMap = new ConcurrentHashMap<>(128);
//        appResourceConfigMap.put("key",null);
        Long id = 0L;
        System.out.println(id <= 0L);
        System.out.println("over");


        Integer l = -2;
        System.out.println(l.toString());

        int a1 = 1000;
        int b1 = 1001;
        int c1 = 1000;
        if (a1 < b1) {
            System.out.println("a1小于b1");
        }
        if (a1 == c1) {
            System.out.println("a1等于c1");
        }
        /**
         * Integer在[-127，128)时，是同一个对象，如果不在这个区间，那么是new的对象，不相等
         * 参考Integer.valueOf()方法
         * int比较不受影响
         * */
        for (int i = 0; i < 150; i++) {
            Integer d = i;
            Integer e = i;
            System.out.println(i + " " + (d == e));
            System.out.println(i + " compare " + (d.equals(e)));
        }
        for (int j = 0; j < 200; j++) {
            int f = j;
            int g = j;
            System.out.println(j + " int compare " + (f == g));
        }
    }
}
