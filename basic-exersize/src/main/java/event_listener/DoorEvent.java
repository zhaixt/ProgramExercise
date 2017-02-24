package event_listener;

import java.util.EventObject;

/**
 * Created by zhaixiaotong on 2016-8-10.
 */
public class DoorEvent extends EventObject {
    private static final long serialVersionUID = 6496098798146410884L;

    private String doorState = "";// 表示门的状态，有“开”和“关”两种

    public DoorEvent(Object source, String doorState) {
        super(source);
        this.doorState = doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }

    public String getDoorState() {
        return this.doorState;
    }
}
