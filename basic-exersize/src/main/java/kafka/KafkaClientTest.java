package kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
/**
 * Created by zhaixt on 2017/7/10.
 */
public class KafkaClientTest {
    public static void main(String[] s) {

        KafkaConsumer<String, String> consumer = KafkaUtil.getConsumer();
        consumer.subscribe(Arrays.asList("braavos-events"));
//        consumer.subscribe(Arrays.asList("test"));//测试环境
        consumer.seekToBeginning(new ArrayList<TopicPartition>());

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10000);
            for (ConsumerRecord<String, String> record : records) {
//                System.out.println("fetched from partition " + record.partition() + ", offset: " + record.offset() + ", message: " + record.value());
                System.out.println("fetched  message: " + record.value());
            }
            //按分区读取数据
//	            for (TopicPartition partition : records.partitions()) {
//	                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
//	                for (ConsumerRecord<String, String> record : partitionRecords) {
//	                    System.out.println(record.offset() + ": " + record.value());
//	                }
//	            }

        }
    }
}
