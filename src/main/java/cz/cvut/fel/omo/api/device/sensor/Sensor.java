package cz.cvut.fel.omo.api.device.sensor;

import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Sensor {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private int id;
    private String name;
    private SensorState state;

    public Sensor(int id, String name, SensorState state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorState getState() {
        return state;
    }

    public void setState(SensorState state) {
        this.state = state;
    }
}
