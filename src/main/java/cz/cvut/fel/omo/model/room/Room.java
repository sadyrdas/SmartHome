package cz.cvut.fel.omo.model.room;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;

import java.util.Objects;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>This class describes room in our house</p>
 */
public class Room {
    private static final Logger LOG = LogManager.getLogger(Room.class.getSimpleName());
    private String roomName;
    private int id;
    private int windowsCount;

    private Set<Device> devices;
    private Set<Human> users;
    private Set<Window> windows;
    private Set<Pet> pets;
    private Set<Sensor> sensors;

    /**
     * Main constructor
     * @param roomName - name of Room
     * @param devices - devices in one room
     * @param users - users in one room
     * @param windows - windows in one room
     * @param sensors - sensors in one room
     */
    public Room(String roomName, Set<Device> devices, Set<Human> users, Set<Window> windows, Set<Sensor> sensors) {
        this.roomName = roomName;
        this.devices = devices;
        this.users = users;
        this.windows = windows;
        this.sensors = sensors;
    }

    public Room(String roomName, int id, int windowsCount, Set<Window> windows) {
        this.roomName = roomName;
        this.id = id;
        this.windowsCount = windowsCount;
        this.windows = windows;
    }

    public Room(int id){
        this.id= id;
    }

    
    public void addUser(Human user) {
        Objects.requireNonNull(user, "Cannot add user to room. User is null!");

        if (users.contains(user)) {
            LOG.warn(user.getName() + " is already in room");
        } else {
            users.add(user);
        }
    }

    public void addDevice(Device device) {
        Objects.requireNonNull(device, "Cannot add user to room. User is null!");

        if (devices.contains(device)) {
            LOG.warn(device.getName() + " is already in room");
        } else {
            devices.add(device);
        }
    }

    public void addWindow(Window window) {
        windows.add(window);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWindowsCount() {
        return windowsCount;
    }

    public void setWindowsCount(int windowsCount) {
        this.windowsCount = windowsCount;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }
}
