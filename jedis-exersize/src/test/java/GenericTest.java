import org.junit.Test;

/**
 * Created by zhaixiaotong on 2016-6-1.
 */
public class GenericTest {
    JedisClusterTest jedisClusterTest = new JedisClusterTest("test11");
    String str = new String();
    int i = 0;
    public  void testObject(Object obj){
        System.out.println("haha");
//        System.out.println()
    }
    public <T> void testGeneric(T obj){
        System.out.println("hehe");
    }

    @Test
    public  void test1(){
       testObject(jedisClusterTest);
    }
    @Test
    public  void test1_1(){
        testObject(str);
    }
    @Test
    public  void test1_2(){
        testObject(i);
    }
    @Test
    public  void test2(){
        testGeneric(jedisClusterTest);
    }
}
