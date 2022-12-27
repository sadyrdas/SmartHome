package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Lamp;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LampApi {
    private static final Logger LOG = LogManager.getLogger(LampApi.class.getSimpleName());
    private Lamp lamp;

    public LampApi(Lamp lamp) {
        this.lamp = lamp;
    }

    public void turnOffAirConditioner() {
        lamp.setState(new StoppedState(lamp));
    }

    public void turnOnAirConditioner() {
        lamp.setState(new ActiveState(lamp));
    }
}
