package kafka;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
/**
 * Created by zhaixt on 2017/7/10.
 */
public class KafkaUtil {
    private static KafkaProducer<String, String> kp;
    private static KafkaConsumer<String, String> kc;

    public static KafkaProducer<String, String> getProducer() {
        if (kp == null) {
            Properties props = new Properties();
            props.put("bootstrap.servers", "10.16.78.21:19092");
//            props.put("bootstrap.servers", "10.18.19.218:9092");//测试
            props.put("acks", "0");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//            props.put("toic","braavos-events")
            kp = new KafkaProducer<String, String>(props);
        }
        return kp;
    }


    public static KafkaConsumer<String, String> getConsumer() {
        if(kc == null) {
            Properties props = new Properties();

            props.put("bootstrap.servers", "10.16.78.21:19092");
//            props.put("bootstrap.servers", "10.18.19.218:9092");//测试
            props.put("group.id", "10");
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("session.timeout.ms", "30000");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("auto.offset.reset", "earliest");//latest / earliest
            kc = new KafkaConsumer<String, String>(props);
        }

        return kc;
    }
}
