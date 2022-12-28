package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.AirConditioner;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirConditionerApi {
    private static final Logger LOG = LogManager.getLogger(AirConditionerApi.class.getSimpleName());
    private final AirConditioner airConditioner;

    public AirConditionerApi(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }



    public void turnOffAirConditioner() {
        airConditioner.setState(new StoppedState(airConditioner));
    }

    public void turnOnAirConditioner() {
        airConditioner.setState(new ActiveState(airConditioner));
    }
}
