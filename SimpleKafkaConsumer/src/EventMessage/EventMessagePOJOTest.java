package eventmessage;

import static org.junit.Assert.*;

/**
 * Created by ISIS,STFC on 02/06/2017.
 * Unit tests for EventMessagePOJO
 */
public class EventMessagePOJOTest {
    private static final int DEFAULT_MESSAGE_ID = 0;
    private static final int DEFAULT_PULSE_TIME = 0;


    /**
     * Testing of setting MessageID
     * @throws Exception Generic Exception
     */
    @org.junit.Test
    public void setMessageId() throws Exception {
        EventMessagePOJO eventMessagePOJO = new EventMessagePOJO(DEFAULT_MESSAGE_ID, DEFAULT_PULSE_TIME);
        assertSame(DEFAULT_MESSAGE_ID, eventMessagePOJO.getMessageId());
        eventMessagePOJO.setMessageId(1);
        assertSame(1, eventMessagePOJO.getMessageId());
    }

}
