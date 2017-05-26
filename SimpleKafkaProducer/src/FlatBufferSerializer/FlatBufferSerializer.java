package FlatBufferSerializer;

import EventMessage.*;
import com.google.flatbuffers.FlatBufferBuilder;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sci28761 on 15/05/2017.
 */
public class FlatBufferSerializer implements org.apache.kafka.common.serialization.Serializer<EventMessagePOJO> {
    @Override
    public byte[] serialize(String topic, EventMessagePOJO eventMessagePOJO) {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        EventMessage.startEventMessage(builder);
        ArrayList<Integer> detectors = eventMessagePOJO.getDetectors();
        for (int i = 0; i < detectors.size();i++) {
            int detectorID = detectors.get(i);
            System.out.println(detectorID);
            EventMessage.addDetectorId(builder,detectorID);
        }

        EventMessage.addMessageId(builder,eventMessagePOJO.getMessageId());
        EventMessage.addPulseTime(builder,eventMessagePOJO.getPulseTime());
        int event = EventMessage.endEventMessage(builder);
        builder.finish(event);
        return builder.sizedByteArray();
    }



    @Override
    public void close() {


    }

    @Override
    public void configure(Map map, boolean b) {

    }
}
