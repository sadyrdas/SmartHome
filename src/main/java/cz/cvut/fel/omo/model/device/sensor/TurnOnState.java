package cz.cvut.fel.omo.model.device.sensor;

import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import cz.cvut.fel.omo.patterns.state.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TurnOnState implements State {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());
    private final Sensor sensor;

    public TurnOnState(Sensor sensor) {
        this.sensor = sensor;
    }


    @Override
    public void setPower() {

    }
}
