import eventmessage.EventMessage;
import flatbufferdeserializer.FlatBufferDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Kafka Consumer For Detection Event Messages.
 */
public final class SimpleKafkaConsumer {

    private SimpleKafkaConsumer() {

    }
    /**
     * Main function for kafka consumer.
     * <p>
     * Connects through sakura to collect all messages from the specified topic, from the beginning.
     * </p>
     * @param args Topic name to consume from.
     * @throws Exception Generic exception
     */
    public static void main(final String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Enter topic name:");
        }

        String topicName =  args[0];
        Properties props = new Properties();

        props.put("bootstrap.servers", "sakura:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        props.put("value.deserializer", FlatBufferDeserializer.class.getName());

        KafkaConsumer<String, EventMessage> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList(topicName));

        System.out.println("Subscribed to topic " + topicName);
        while (true) {
            ConsumerRecords<String, EventMessage> records = consumer.poll(100);


            for (ConsumerRecord<String, EventMessage> record: records) {

                EventMessage eventMessagePOJO = record.value();
                System.out.println(eventMessagePOJO.messageId());
                System.out.println(eventMessagePOJO.pulseTime());

            }
        }
    }
}
