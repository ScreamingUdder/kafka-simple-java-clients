package EventMessage;

import java.util.ArrayList;

/**
 * Created by sci28761 on 24/05/2017.
 */
public class EventMessagePOJO {
    private int messageId;
    private long pulseTime;
    private ArrayList<Integer> detectors;

    public EventMessagePOJO(int messageId,long pulseTime) {
        this.messageId = messageId;
        this.pulseTime = pulseTime;
    }

    public void setMessageId(int messageId) { this.messageId = messageId;}
    public int getMessageId() { return  messageId;}

    public void setPulseTime(long pulseTime) { this.pulseTime = pulseTime;}
    public long getPulseTime() { return  pulseTime;}

    public void setDetectors(ArrayList detectors) {this.detectors = detectors;}
    public ArrayList getDetectors() {return detectors;};
    public void addDetector(int detector) {detectors.add(detector);}
    public int getDetector(int index) { return detectors.get(index);}
}
