import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaixiaotong on 2016-6-1.
 */
public class ListAssinmentTest {
    @Test
    public void listTest() {
        List<String> test = new ArrayList<String>();
        test.add("hehe");
        test.add("haha");
        List<String> test2 = new ArrayList<String>();
        test2.add("meme");
        test2.add("tata");
//        test2.add("lalala");
        test = test2;
        System.out.println(test.get(1));
    }
}
