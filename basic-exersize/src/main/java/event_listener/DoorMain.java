package event_listener;

/**
 * Created by zhaixiaotong on 2016-8-10.
 */
public class DoorMain {
    public static void main(String[] args) {
        DoorManager manager = new DoorManager();
        manager.addDoorListener(new ConcreteDoorListener1());// 给门1增加监听器
        manager.addDoorListener(new ConcreteDoorListener2());// 给门2增加监听器
        // 开门
        manager.fireWorkspaceOpened();
        System.out.println("我已经进来了");
        // 关门
        manager.fireWorkspaceClosed();
    }
}
