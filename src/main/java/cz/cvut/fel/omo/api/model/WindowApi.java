package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.room.Room;

import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * <p>Provides API for Window which includes access Windows in house.</p>
 */
public class WindowApi {
    private static final Logger LOG = LogManager.getLogger(WindowApi.class.getSimpleName());
    private final Set<Room> rooms;

    /**
     * Main constructor
     * @param rooms - set of rooms, in which windows
     */
    public WindowApi(Set<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * open the window, set Boolean isOpen to TRUE
     */
    public void openWindow() {
        for (Room r : rooms) {
            for (Window w : r.getWindows()) {
                w.open();
            }
        }
    }

    /**
     * close the window, set Boolean isOpen to FALSE
     */
    public void closeWindow() {
        for (Room r : rooms) {
            for (Window w : r.getWindows()) {
                w.close();
            }
        }
    }
}
