package flatbufferdeserializer;

import eventmessage.EventMessage;
import eventmessage.EventMessagePOJO;

import java.util.Map;

/**
 * Created by ISIS,STFC on 15/05/2017.
 */
public class FlatBufferDeserializer implements org.apache.kafka.common.serialization.Deserializer<EventMessagePOJO> {
    /**
     *
     * @param topic Kafka topic name
     * @param bytes Bytes received by Kafka consumer
     * @return EventMessagePOJO
     */
    @Override
    public EventMessagePOJO deserialize(final String topic, final byte[] bytes) {
        EventMessage eventMessage = EventMessage.getRootAsEventMessage(java.nio.ByteBuffer.wrap(bytes));
        int eventMessageId = (int) eventMessage.messageId();
        EventMessagePOJO eventMessagePOJO = new EventMessagePOJO(eventMessageId, eventMessage.pulseTime());

        for (int i = 0; i < eventMessage.detectorIdLength(); i++) {
            int detectorID = (int) eventMessage.detectorId(i);
            System.out.println(eventMessagePOJO.getPulseTime());
            eventMessagePOJO.addDetector(detectorID);
        }
        return eventMessagePOJO;
    }



    @Override
    public void close() {


    }

    @Override
    public void configure(final Map map, final boolean b) {

    }
}
