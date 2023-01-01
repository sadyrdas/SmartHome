package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.AirConditioner;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

/**
 * <p>Provides API for air conditioner which includes access for all air conditioners in house.</p>
 */
public class AirConditionerApi {
    private static final Logger LOG = LogManager.getLogger(AirConditionerApi.class.getSimpleName());
    private final Set<AirConditioner> airConditioners;
    private final SimulationFacade simulationFacade;

    /**
     * Main constructor
     * @param airConditioners - set of all air conditioners in house
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     */
    public AirConditionerApi(Set<AirConditioner> airConditioners, SimulationFacade simulationFacade) {
        this.airConditioners = airConditioners;
        this.simulationFacade = simulationFacade;
    }

    /**
     * Get all air conditioners in house
     */
    public Set<AirConditioner> getAirConditioners() {
        return airConditioners;
    }

    /**
     * Turn off-air conditioner. Set state of device to Stopped.
     * @param human the one user who does the action
     * @param id unique id of one specific air conditioner
     */
    public void turnOffAirConditionerById(Human human, Integer id) {
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
            human.countDeviceUsage(airConditioner);
        }
    }

    /**
     * Turn on-air conditioner. Set state of device to Active.
     * @param human the one user who does the action
     * @param id unique id of one specific air conditioner
     */

    public void turnOnAirConditionerById(Human human, Integer id) {
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
            human.countDeviceUsage(airConditioner);
        }
    }
}
