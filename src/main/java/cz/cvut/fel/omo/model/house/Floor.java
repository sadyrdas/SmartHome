package cz.cvut.fel.omo.model.house;

import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Floor {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());
    private final int id;
    private int number;
    private Set<Room> rooms;

    public Floor(int id, int number, Set<Room> rooms) {
        this.id = id;
        this.number = number;
        this.rooms = rooms;
    }

    public Floor(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRooms(Room room) {
        Objects.requireNonNull(room);
        if (rooms == null)
            rooms = new HashSet<>();
        rooms.add(room);

    }
}
