package FlatBufferSerializer;

import KafkaMessage.Message;
import com.google.flatbuffers.FlatBufferBuilder;

import java.util.Map;

/**
 * Created by sci28761 on 15/05/2017.
 */
public class FlatBufferSerializer implements org.apache.kafka.common.serialization.Serializer<String> {
    @Override
    public byte[] serialize(String topic, String message) {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int contents = builder.createString(message);
        Message.startMessage(builder);
        Message.addContents(builder,contents);
        int numberMessage = Message.endMessage(builder);
        builder.finish(numberMessage);
        return builder.sizedByteArray();
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map map, boolean b) {

    }
}
