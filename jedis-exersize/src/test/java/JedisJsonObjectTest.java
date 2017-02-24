import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Created by zhaixiaotong on 2016-6-1.
 */
public class JedisJsonObjectTest {
    @Test
    public void JsonTest(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key","valueee");
        jsonObject.put("version", 1);
        String s = jsonObject.toJSONString();
        System.out.println(s);
        JSONObject jo2 = JSON.parseObject(s);
        System.out.println(jo2.get("key")+jo2.get("version").toString());
    }
}
