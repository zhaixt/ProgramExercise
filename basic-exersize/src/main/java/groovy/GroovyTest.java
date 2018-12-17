package groovy;

import groovy.lang.GroovyShell;

import java.util.Date;


/**
 * Created by zhaixt on 2017/8/23.
 */
public class GroovyTest {
    public static void main(String[] args) {
        GroovyShell shell = new GroovyShell();
//        String groovyString=" return new Date().format('yyyy-MM-dd')";
//        String groovyString=" def now = new Date() ; return now.format('yyyy-MM-dd')";
        String groovyString="Calendar c = Calendar.getInstance();c.setTime(new Date());" +
                "c.add(5, -1);" +
                "return c.getTime().format('yyyy-MM-dd')";

        String groovyString2 = "import java.time.LocalDate\n" +
                "import java.time.LocalDateTime\n" +
                "import java.time.format.DateTimeFormatter\n" +
                "import java.time.Instant\n" +
                "import java.util.TimeZone\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import static java.time.temporal.ChronoUnit.DAYS\n" +
                "applyTime = LocalDateTime.parse(\"2016-11-01T00:00\").toLocalDate().toString();";

        String groovyString3 = "import java.time.LocalDate\n" +
                "import java.time.LocalDateTime\n" +
                "import java.time.format.DateTimeFormatter\n" +
                "import java.time.Instant\n" +
                "import java.util.TimeZone\n" +
                "import java.text.SimpleDateFormat;\n" +
                "import static java.time.temporal.ChronoUnit.DAYS\n" +
                "birthday = \"20170605\"\n"+
                "def formater =  DateTimeFormatter.ofPattern(\"yyyyMMdd\")\n" +
                "def birthdate= LocalDate.parse(birthday, formater).atStartOfDay().toLocalDate().toString()";

        String result = (String) shell.evaluate(groovyString);
        String result2 = (String) shell.evaluate(groovyString2);
        String result3 = (String) shell.evaluate(groovyString3);
        System.out.println(result2);
        System.out.println(result3);




    }
}
