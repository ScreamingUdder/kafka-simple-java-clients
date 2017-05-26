package FlatBufferDeserializer;

import EventMessage.EventMessage;
import EventMessage.EventMessagePOJO;

import java.util.Map;

/**
 * Created by sci28761 on 15/05/2017.
 */
public class FlatBufferDeserializer implements org.apache.kafka.common.serialization.Deserializer<EventMessagePOJO> {
    @Override
    public EventMessagePOJO deserialize(String topic, byte[] bytes) {
        EventMessage eventMessage = EventMessage.getRootAsEventMessage(java.nio.ByteBuffer.wrap(bytes));
        EventMessagePOJO eventMessagePOJO = new EventMessagePOJO((int) eventMessage.messageId(),eventMessage.pulseTime());
        for (int i = 0; i < eventMessage.detectorIdLength();i++) {
            int detectorID = (int) eventMessage.detectorId(i);
            //System.out.println(detectorID);
            System.out.println(eventMessagePOJO.getPulseTime());
            eventMessagePOJO.addDetector(detectorID);
        }
        return eventMessagePOJO;
    }



    @Override
    public void close() {


    }

    @Override
    public void configure(Map map, boolean b) {

    }
}
