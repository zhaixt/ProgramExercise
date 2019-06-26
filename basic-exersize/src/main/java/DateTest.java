import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.util.TimeZone;

/**
 * Created by zhaixt on 2018/4/20.
 */
public class DateTest {
    public static void main(String[] args) {
//        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Long time=new Long(1500520271000l);
//        String d = format.format(time);
//        Date date = null;
//        try {
//             date= format.parse(d);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.print(date.toString());


        DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss.SSS");
        df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1471337924226L), ZoneId.of("Asia/Shanghai")));
        try {
//            Date date = new SimpleDateFormat("yyyyMMdd").parse("20170601");
//            LocalDate localDate = LocalDateTime.parse("2017-09-28 17:07:05").toLocalDate();
//            System.out.println(localDate);


            String startTime = "2016-11-16 00:00";
            DateTimeFormatter sf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startLocalDate = LocalDateTime.parse(startTime, sf);
            DateTimeFormatter sf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String endTime = "2016-11-01 00:00";
            LocalDateTime startLocalDate2 = LocalDateTime.parse(endTime, sf2);
            System.out.println(startLocalDate2);

            String birthday = "20110203";
            DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate birthdate = LocalDate.parse(birthday, formater).atStartOfDay().toLocalDate();
            System.out.println("birthday:" + birthdate.toString());

            //201203这种是不行的
//            String birthday2 = "201102";
//            DateTimeFormatter formater2 =  DateTimeFormatter.ofPattern("yyyyMM");
//            LocalDate birthdate2= LocalDate.parse(birthday2, formater).atStartOfDay().toLocalDate();
//            System.out.println("birthday:"+birthdate2.toString());

            DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter sf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime2 = LocalDateTime.now();
            String startTimeStr = startTime2.format(df2);

            LocalDateTime localDateTime = LocalDateTime.parse(startTimeStr, df2);
            ZoneId zone = ZoneId.systemDefault();
            Instant instant = localDateTime.atZone(zone).toInstant();
            java.util.Date date = Date.from(instant);
            System.out.println(date.toString());


            SimpleDateFormat miniteDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date currentTime = new Date();
            SimpleDateFormat second = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = second.format(currentTime);
//            ParsePosition pos = new ParsePosition(8);
            Date currentTime_2 = miniteDateFormat.parse(dateString);
            System.out.println(currentTime_2);

            Date currentMinite = getIntegralMinite(new Date());
            System.out.println("current minite:"+currentMinite);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Date getIntegralMinite(Date currentTime) throws ParseException {
        SimpleDateFormat miniteDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat second = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = second.format(currentTime);
        Date currentMinite = miniteDateFormat.parse(dateString);
        return currentMinite;

    }
}
