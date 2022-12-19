package cz.cvut.fel.omo.model.house;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
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

    private final Set<RoomBase> rooms = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();
    private final Set<Human> humans = new HashSet<>();
    private final Set<Pet> pets = new HashSet<>();
    private final Set<Floor> floors = new HashSet<>();
    private final Set<Sensor> sensors = new HashSet<>();

    public House() {

    }


    public void addRoom(RoomBase roomBase){
        rooms.add(roomBase);
    }

    public void addDevice(Device device){
        devices.add(device);
    }
    public void addHuman(Human human){
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

    public Set<RoomBase> getRooms() {
        return rooms;
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
}
