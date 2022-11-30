package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.api.device.Device;
import cz.cvut.fel.omo.api.device.Window;
import cz.cvut.fel.omo.api.device.sensor.Sensor;
import cz.cvut.fel.omo.api.room.Room;
import cz.cvut.fel.omo.api.user.Human;
import cz.cvut.fel.omo.api.user.Pet;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class RoomBuilder {

    private static final Logger LOGGER = LogManager.getLogger(RoomBuilder.class.getName());

    private String roomName;

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

    public Room build() {

        if (roomName.isEmpty()) {
            // TODO can we do better than just return null?
            LOGGER.error("Room can not be created without name!");
        }

        return new Room(roomName);
    }
}
