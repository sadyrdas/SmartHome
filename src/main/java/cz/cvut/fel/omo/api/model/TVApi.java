package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Fridge;
import cz.cvut.fel.omo.model.device.TV;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TVApi {
    private static final Logger LOGGER = LogManager.getLogger(TVApi.class.getName());
    private final TV tv;


    public TVApi(TV tv) {
        this.tv = tv;
    }

    public void turnOfTv() {
        tv.setState(new StoppedState(this.tv));
    }

    public void turnOnTv() {
        tv.setState(new ActiveState(this.tv));
    }
}
