import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * Created by zhaixiaotong on 2016-11-28.
 */
public class AliDayuTest {
    private static final String ALIDAYU_SEND_SMS_REQUEST_URL = "https://eco.taobao.com/router/rest";

    public static void main(String[] args) {


        String appKey = "23550202";
        String appSecret = "cf34d201d84d8fd2b80bf880db6846ca";
        TaobaoClient client = new DefaultTaobaoClient(ALIDAYU_SEND_SMS_REQUEST_URL, appKey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("阿里大于");
        req.setSmsParamString("{\"code\":\"1234\",\"product\":\"alidayu\"}");
        req.setRecNum("13000000000");
        req.setSmsTemplateCode("SMS_585014");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }
}
