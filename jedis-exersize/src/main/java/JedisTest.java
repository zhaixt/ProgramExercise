import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaixiaotong on 2016-5-16.
 */
public class JedisTest{

    public static void main(String[] args){
        Jedis jedis = new Jedis("10.253.11.47",6379);
//        Jedis jedis2 = new Jedis("10.253.101.145",6380);

        long value = System.currentTimeMillis() +60 + 1;

        long acquired = jedis.setnx("l23",String.valueOf(value));
//        long acquired = jedis.setnx("l3",String.valueOf(value));
//        String acquired = jedis.set("121","test3");
//            long acquired = jedis.del("123");
//        long acquired = jedis.expire("13",10);
        System.out.println("-----result:"+acquired);


//        Long temp = jedis.incr("put3");
//        String result = jedis.flushAll();
//         JedisClusterTest test = new JedisClusterTest("1");
//        jedis.mset()
//        jedis.rpush("test1",test);
//        List<String> result = jedis.configGet("maxmemory");
//        String result2 = jedis.configSet("maxmemory2","10000");
//        String result2 = replaceComma(result);
//        String result = jedis.info("Replication");
//        String result2 = jedis2.info("Replication");
//        System.out.println(result);
        System.out.println("--------------------------------");
//        System.out.println(result2);
//        System.out.println(jedis.setnx("k1","v1"));
//        jedis.set("lls","21");
//        long incrResult = jedis.incr("lls");
//        System.out.println("incr result:"+incrResult);
//        jedis.set("tt", "haqi");
//        long incrResult2 = 0;//对于value是字符串，不是数字，直接拋异常
//        try {
//            incrResult2 = jedis.incr("tt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("incr result:"+incrResult2);
//
//        Object obj = getObject(123);
//        if(obj instanceof Integer){
//            System.out.println("yes int");
//        }
//        String setResult = jedis.setex("ex", 100, obj.toString());
//        System.out.println("Set Result:"+setResult);
//        int res = Integer.parseInt(jedis.get("ex"));
////        String res = jedis.get("ex");
//        System.out.println(res);
//        Object obj2 = getObject("lalaal");
//        if(obj2 instanceof String){
//            System.out.println("yes string");
//        }
//        byte[] aa = jedis.get("2".getBytes());
//        jedis.setex("ex2",100,obj2.toString());
//        String res2 = (String)jedis.get("ex2");
//        System.out.println(res2);
//        String objString = obj.toString();
//        String result = jedis.get("llmm");
//        String result2 = jedis.info("Memory");
//        System.out.println("result is :"+result);
        //System.out.println("result2 is :"+result2);


    }
    private String parseRedisInfo(String info){
//        RedisInfo redisInfo = new RedisInfo();
        String infoDealed = replaceComma(info);
        String[] infoArray = info.split(",");
        for(String element : infoArray ){
            if(element.contains(":")){
                String[] elementArray = element.split(":");
//                switch(elementArray[0]){
//                    case "used_memory":
//
//                }

            }
        }
        return "";
    }
    private static String replaceComma(String str) {
        String dest = "";
        if (str!=null) {
//            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Pattern p = Pattern.compile("\r\n|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll(",");
        }
        return dest;
    }

    private static Object getObject(Object arg){
        return arg;
    }
}
