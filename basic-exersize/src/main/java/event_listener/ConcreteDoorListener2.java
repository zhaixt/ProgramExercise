package event_listener;

/**
 * Created by zhaixiaotong on 2016-8-10.
 */
public class ConcreteDoorListener2 implements DoorListener {
    @Override
    public void doorEvent(DoorEvent event) {
        // TODO Auto-generated method stub
        if (event.getDoorState() != null && event.getDoorState().equals("open")) {
            System.out.println("门2打开,同时打开走廊的灯");
        } else {
            System.out.println("门2关闭,同时关闭走廊的灯");
        }
    }

}
