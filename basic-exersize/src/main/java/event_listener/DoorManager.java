package event_listener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by zhaixiaotong on 2016-8-10.
 */

/*通过DoorManager.java创造一个事件源类，它用一个Collection listeners对象来存储所有的事件监听器对象，存储方式是通过addDoorListener(..)这样的方法。
  notifyListeners(..)是触发事件的方法，用来通知系统：事件发生了，你调用相应的处理函数吧。*/
/**
 * 事件源对象，在这里你可以把它想象成一个控制开门关门的遥控器，
 * (如果是在swing中，就类似一个button)
 */

public class DoorManager {
    private Collection listeners;

    /**
     * 添加事件
     *
     * @param listener
     *            DoorListener
     */
    public void addDoorListener(DoorListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    /**
     * 移除事件
     *
     * @param listener
     *            DoorListener
     */
    public void removeDoorListener(DoorListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    /**
     * 触发开门事件
     */
    protected void fireWorkspaceOpened() {
        if (listeners == null)
            return;
        DoorEvent event = new DoorEvent(this, "open");
        notifyListeners(event);
    }

    /**
     * 触发关门事件
     */
    protected void fireWorkspaceClosed() {
        if (listeners == null)
            return;
        DoorEvent event = new DoorEvent(this, "close");
        notifyListeners(event);
    }

    /**
     * 通知所有的DoorListener
     */
    private void notifyListeners(DoorEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            DoorListener listener = (DoorListener) iter.next();
            listener.doorEvent(event);
        }
    }
}

