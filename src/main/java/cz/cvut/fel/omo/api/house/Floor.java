package cz.cvut.fel.omo.api.house;

import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Floor {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());


    private int id;
    private List<RoomBase> rooms;

    public Floor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
