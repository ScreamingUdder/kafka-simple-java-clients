import FlatBufferSerializer.FlatBufferSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

class SimpleKafkaProducer {

    public static void main(String[] args) throws Exception {
        String topicName;
        if(args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter topic name:");
            topicName = scanner.next();
            scanner.close();
        } else {
            topicName = args[0].toString();
        }

        Properties props = new Properties();
        props.put("bootstrap.servers","sakura:9092");
        props.put("acks","all");// set acknowledgements to true
        props.put("retries",0); //disable automatic retries
        props.put("batch.size",16384); //buffer size
        props.put("linger.ms",1);
        props.put("buffer.memory",33444432); // memory available to the buffer
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", FlatBufferSerializer.class);

        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord(topicName,Integer.toString(i)));
            System.out.println("Message sent successfully");
        }

        producer.close();

    }
}