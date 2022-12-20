package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class RoomBuilder {

    private static final Logger LOGGER = LogManager.getLogger(RoomBuilder.class.getName());

    private String roomName;
    private int id;
    private int windowsCount;

    private Set<Device> devices;
    private Set<Human> humans;
    private Set<Window> windows;

    private Set<Pet> pets;
    private Set<Sensor> sensors;

    public RoomBuilder addRoomName(String name) {
        this.roomName = name;
        return this;
    }

    public RoomBuilder addDevicesToRoom(Set<Device> devices) {
        this.devices = devices;
        return this;
    }

    public RoomBuilder addUsersToRoom(Set<Human> humans) {
        this.humans = humans;
        return this;
    }
    public RoomBuilder addWindowsToRoom(Set<Window> windows) {
        this.windows = windows;
        return this;
    }
    public RoomBuilder addPetsToRoom(Set<Pet> pets) {
        this.pets = pets;
        return this;
    }

    public RoomBuilder addSensorsToRoom(Set<Device> devices) {
        this.devices = devices;
        return this;
    }

    public RoomBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public RoomBuilder setWindowsCount(int windowsCount) {
        this.windowsCount = windowsCount;
        return this;
    }

    public Room build() {

        if (roomName.isEmpty() || this.id < 1) {
            LOGGER.error("Room can not be created without name!");
        }

        return new Room(this.roomName, this.windowsCount, this.id);
    }
}
