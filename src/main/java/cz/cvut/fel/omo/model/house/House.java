package cz.cvut.fel.omo.model.house;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;
import cz.cvut.fel.omo.model.user.Resident;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class House {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private final Set<Room> rooms = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();
    private final Set<Human> humans = new HashSet<>();
    private final Set<Pet> pets = new HashSet<>();
    private final Set<Floor> floors = new HashSet<>();
    private final Set<Sensor> sensors = new HashSet<>();
    private final Set<Window> windows = new HashSet<>();

    public House() {

    }


    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void addHuman(Human human) {
        humans.add(human);
    }

    public void removeHuman(Human human) {
        humans.remove(human);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void addWindow(Window window) {
        windows.add(window);
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public Room getRoomById(int id) {
        for (Floor f : getFloors()) {
            for (Room r : f.getRooms()) {
                if (r != null && r.getId() == id) {
                    return r;
                }
            }
        }
        return null;
    }


    public Set<Device> getDevices() {
        return devices;
    }

    public Set<Human> getHumans() {
        return humans;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public Set<Floor> getFloors() {
        return floors;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public Set<Window> getWindows() {
        return windows;
    }

}
