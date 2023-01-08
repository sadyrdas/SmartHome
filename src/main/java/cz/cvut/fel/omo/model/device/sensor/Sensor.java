package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.model.device.energy.Energy;
import cz.cvut.fel.omo.model.device.energy.EnergyType;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import cz.cvut.fel.omo.patterns.observer.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>This abstract class describes Sensor in house.</p>
 */
public abstract class Sensor implements Subject {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());
    private int id;
    private String name;
    private SensorState state;
    private int baseEnergyConsumption;
    private Energy energy;
    private Room room;

    /**
     * Main Constructor
     *
     * @param id                    - unique specific of sensor
     * @param sensorName            - name of sensor
     * @param baseEnergyConsumption - base energy consumption during actions for day.
     * @param room                  - room, where this state
     * @param energyType            - type of Energy - Electricity
     */
    public Sensor(int id, String sensorName, int baseEnergyConsumption, Room room, EnergyType energyType) {
        this.id = id;
        this.name = sensorName;
        this.baseEnergyConsumption = baseEnergyConsumption;
        this.room = room;
        setState(new ActiveSensorState(this));
        setEnergy(new Energy(baseEnergyConsumption, energyType));
    }

    private void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public Energy getEnergy() {
        return energy;
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

    public int getBaseEnergyConsumption() {
        return baseEnergyConsumption;
    }

    public void setBaseEnergyConsumption(int baseEnergyConsumption) {
        this.baseEnergyConsumption = baseEnergyConsumption;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
