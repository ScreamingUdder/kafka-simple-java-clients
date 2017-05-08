import KafkaMessage.Message;
import com.google.flatbuffers.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

class SimpleKafkaProducer {

    public static void main(String[] args) throws Exception {
        if(args.length == 0) {
            System.out.println("Enter topic name:");
        }

        String topicName = args[0].toString();
        Properties props = new Properties();
        props.put("bootstrap.servers","sakura:9092");
        // set acknowledgements to true
        props.put("acks","all");
        props.put("retries",0); //disable automatic retries
        props.put("batch.size",16384); //buffer size
        props.put("linger.ms",1); // not too sure about this one to be honest
        props.put("buffer.memory",33444432); // memory available to the buffer, which is somehow different to the buffer size
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        FlatBufferBuilder builder = new FlatBufferBuilder(1024);



        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            int contents = builder.createString(Integer.toString(i));
            Message.startMessage(builder);
            Message.addContents(builder,contents);
            int numberMessage = Message.endMessage(builder);
            builder.finish(numberMessage);
            producer.send(new ProducerRecord(topicName, builder.sizedByteArray()));
            System.out.println("Message sent successfully");
        }

        producer.close();

    }
}