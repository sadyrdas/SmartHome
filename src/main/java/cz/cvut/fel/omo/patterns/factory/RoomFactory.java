package cz.cvut.fel.omo.patterns.factory;

import cz.cvut.fel.omo.api.device.Device;
import cz.cvut.fel.omo.api.device.Fridge;
import cz.cvut.fel.omo.api.room.Room;
import cz.cvut.fel.omo.api.user.Human;
import cz.cvut.fel.omo.api.user.Pet;
import cz.cvut.fel.omo.patterns.state.State;

import java.util.List;
import java.util.Set;

public class RoomFactory {

    public Room createEmptyRoom(String roomName) {
        return new Room(roomName);
    }

    public Room createRoomWithDevicesHumans(String roomName, Set<Device> devices, Set<Human> humans) {
        Room room = new Room(roomName);
        room.setDevices(devices);
        room.setUsers(humans);
        return new Room(roomName);
    }

    public Room createRoomWithHumansAndPets(String roomName, Set<Pet> pets, Set<Human> humans) {
        Room room = new Room(roomName);
        room.setPets(pets);
        room.setUsers(humans);
        return new Room(roomName);
    }

}
