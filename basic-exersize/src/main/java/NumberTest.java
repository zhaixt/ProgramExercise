import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by zhaixt on 2018/4/20.
 */
public class NumberTest {
    public static void main(String[] args){
//        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int a = 12;
        Integer b = 10;
        Double c = 10.2;
        String str = b.toString();
        String str2 = c.toString();
        BigDecimal d1 = new BigDecimal(a);
        BigDecimal d2 = new BigDecimal(str);
        BigDecimal d3 = new BigDecimal(str2);
        System.out.println("over");


    }
}
