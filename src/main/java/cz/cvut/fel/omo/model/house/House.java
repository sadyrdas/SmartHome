package cz.cvut.fel.omo.model.house;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.user.Resident;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class House {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private final Set<RoomBase> rooms;
    private final Set<Device> devices;
    private final Set<Resident> residents;
    private final Set<Floor> floors;

    public House(Set<RoomBase> rooms, Set<Device> devices, Set<Resident> residents, Set<Floor> floors) {
        this.rooms = rooms;
        this.devices = devices;
        this.residents = residents;
        this.floors = floors;
    }


    public void addRoom(RoomBase roomBase){
        rooms.add(roomBase);
    }

    public void addDevice(Device device){
        devices.add(device);
    }
    public void addResident(Resident resident){
        residents.add(resident);
    }

    public void removeResident(Resident resident) {
        residents.remove(resident);
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }
}
