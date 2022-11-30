package cz.cvut.fel.omo.api.device.sensor;

import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import cz.cvut.fel.omo.patterns.state.State;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;

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
