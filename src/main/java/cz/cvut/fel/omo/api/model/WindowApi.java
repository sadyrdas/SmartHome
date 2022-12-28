package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.room.Room;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WindowApi {
    private static final Logger LOG = LogManager.getLogger(WindowApi.class.getSimpleName());
    private final Set<Room> rooms;

    public WindowApi(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void openWindow() {
        for (Room r : rooms) {
            for (Window w : r.getWindows()) {
                w.open();
            }
        }
    }

    public void closeWindow() {
        for (Room r : rooms) {
            for (Window w : r.getWindows()) {
                w.close();
            }
        }
    }
}
