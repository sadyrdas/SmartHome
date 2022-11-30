package cz.cvut.fel.omo.model.room;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;

import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

public class Room {
    private static final Logger LOG = Logger.getLogger(Room.class.getSimpleName());
    private final String roomName;

    private Set<Device> devices;
    private Set<Human> users;
    private Set<Window> windows;

    private Set<Pet> pets;

    public Room(String roomName, Set<Device> devices, Set<Human> users, Set<Window> windows) {
        this.roomName = roomName;
        this.devices = devices;
        this.users = users;
        this.windows = windows;
    }

    public Room(String roomName) {
        this.roomName = roomName;
    }

    
    public void addUser(Human user) {
        Objects.requireNonNull(user, "Cannot add user to room. User is null!");

        if (users.contains(user)) {
            LOG.warning(user.getName() + " is already in room");
        } else {
            users.add(user);
        }
    }

    public void addDevice(Device device) {
        Objects.requireNonNull(device, "Cannot add user to room. User is null!");

        if (devices.contains(device)) {
            LOG.warning(device.getName() + " is already in room");
        } else {
            devices.add(device);
        }
    }

    public String getRoomName() {
        return roomName;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public Set<Human> getUsers() {
        return users;
    }

    public void setUsers(Set<Human> users) {
        this.users = users;
    }

    public Set<Window> getWindows() {
        return windows;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
