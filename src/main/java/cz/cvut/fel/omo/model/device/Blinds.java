package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.room.Room;

import java.util.logging.Logger;

public class Blinds {

    private final Logger LOG = Logger.getLogger(this.getClass().getSimpleName());
    private final Room room;
    private final Window window;
    private Boolean isOpen = true;

    public Blinds(Room room, Window window) {
        this.room = room;
        this.window = window;
    }

    public void closeBlinds() {
        this.isOpen = false;
    }

    public void openBlinds() {
        this.isOpen = true;
    }
}