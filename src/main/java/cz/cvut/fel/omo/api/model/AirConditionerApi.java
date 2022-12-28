package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.AirConditioner;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

public class AirConditionerApi {
    private static final Logger LOG = LogManager.getLogger(AirConditionerApi.class.getSimpleName());
    private final Set<AirConditioner> airConditioners;

    public AirConditionerApi(Set<AirConditioner> airConditioners) {
        this.airConditioners = airConditioners;
    }

    public Set<AirConditioner> getAirConditioners() {
        return airConditioners;
    }

    public void turnOffAirConditionerById(Integer id) {
        for (AirConditioner ac : airConditioners) {
            if (ac.getId() == id) {
                ac.setState(new StoppedState(ac));
            }        
        }
        LOG.info("AirConditioner with id: " + id + " was turned off!");
    }

    public void turnOnAirConditionerById(Integer id) {
        for (AirConditioner ac : airConditioners) {
            if (ac.getId() == id) {
                ac.setState(new ActiveState(ac));
            }
        }
        LOG.info("AirConditioner with id: " + id + " was turned on!");
    }
}
