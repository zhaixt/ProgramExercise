package event_listener;

import java.util.EventListener;

/**
 * Created by zhaixiaotong on 2016-8-10.
 */
public interface DoorListener extends EventListener {
    public void doorEvent(DoorEvent event);
}
