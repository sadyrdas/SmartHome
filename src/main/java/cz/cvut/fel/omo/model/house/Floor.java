package cz.cvut.fel.omo.model.house;

import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Floor {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());


    private final int id;
    private final int number;
    private List<RoomBase> rooms;

    public Floor(int id, int number, List<RoomBase> rooms) {
        this.id = id;
        this.number = number;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public List<RoomBase> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomBase> rooms) {
        this.rooms = rooms;
    }
}
