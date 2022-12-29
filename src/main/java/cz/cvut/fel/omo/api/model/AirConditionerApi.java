package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.AirConditioner;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class AirConditionerApi {
    private static final Logger LOG = LogManager.getLogger(AirConditionerApi.class.getSimpleName());
    private final Set<AirConditioner> airConditioners;
    private final SimulationFacade simulationFacade;

    public AirConditionerApi(Set<AirConditioner> airConditioners, SimulationFacade simulationFacade) {
        this.airConditioners = airConditioners;
        this.simulationFacade = simulationFacade;
    }

    public Set<AirConditioner> getAirConditioners() {
        return airConditioners;
    }

    public void turnOffAirConditionerById(Integer id) {
        AirConditioner airConditioner = null;
        for (AirConditioner ac : airConditioners) {
            if (ac.getId() == id) {
                ac.setState(new StoppedState(ac));
                airConditioner = ac;
            }        
        }
        if (airConditioner != null) {
            LOG.info("AirConditioner with id: " + id + " was turned off!");
            simulationFacade.addDeviceEventsTypeToEventsHub(airConditioner, EventsType.Turn_off_device);
        }
    }

    public void turnOnAirConditionerById(Integer id) {
        AirConditioner airConditioner = null;

        for (AirConditioner ac : airConditioners) {
            if (ac.getId() == id) {
                ac.setState(new ActiveState(ac));
                airConditioner = ac;
            }
        }
        if (airConditioner != null) {
            LOG.info("AirConditioner with id: " + id + " was turned on!");
            simulationFacade.addDeviceEventsTypeToEventsHub(airConditioner, EventsType.Turn_on_device);

        }
    }
}
