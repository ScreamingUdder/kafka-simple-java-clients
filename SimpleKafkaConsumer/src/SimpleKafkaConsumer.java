import KafkaMessage.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;


/**
 * Created by sci28761 on 07/04/2017.
 */
public class SimpleKafkaConsumer {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Enter topic name:");
        }

        String topicName =  args[0].toString();
        Properties props = new Properties();

        props.put("bootstrap.servers", "sakura:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

        KafkaConsumer<byte[],byte[]> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topicName));

        System.out.println("Subscribed to topic " + topicName);
        int i = 0;

        while (true) {
            ConsumerRecords<byte[],byte[]> records = consumer.poll(100);


            for (ConsumerRecord<byte[],byte[]> record: records) {

                byte[] bytes = record.value();

                java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(bytes);
                Message message = Message.getRootAsMessage(buf);

                System.out.printf("offset = %d, key = %s, value = %s\n",record.offset(), record.key(), message.contents());

            }
        }
    }
}