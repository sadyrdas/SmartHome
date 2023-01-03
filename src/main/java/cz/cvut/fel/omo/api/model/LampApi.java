package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Lamp;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

/**
 * <p>Provides API for lamp which includes access all lamps in house.</p>
 */
public class LampApi {
    private static final Logger LOG = LogManager.getLogger(LampApi.class.getSimpleName());
    private final SimulationFacade simulationFacade;


    public Set<Lamp> getLamps() {
        return lamps;
    }

    private final Set<Lamp> lamps;

    /**
     * Main constructor
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     * @param lamps - set of all lamps in our house
     */
    public LampApi(SimulationFacade simulationFacade, Set<Lamp> lamps) {
        this.simulationFacade = simulationFacade;
        this.lamps = lamps;
    }

    /**
     * Turn off - lamp. Set state of device to Stopped.
     * @param human - the one user who does the action
     * @param id unique id of one specific lamp
     */

    public void turnOffLamp(Human human, Integer id) {
        Lamp lamp = getLampById(id);
        if (lamp == null) {
            LOG.error("Lamp is null. Cannot turn off lamp.");
        } else {
            LOG.info("Lamp was turned off");
            lamp.setState(new StoppedState(lamp));
            simulationFacade.addDeviceEventsTypeToEventsHub(lamp, EventsType.Turn_off_device);
            human.countDeviceUsage(lamp);
        }
    }

    /**
     * Turn on - lamp. Set state of device to Active
     * @param human the one user who does the action
     * @param id unique id of one specific lamp
     */

    public void turnOnLamp(Human human, Integer id) {
        Lamp lamp = getLampById(id);

        if (lamp == null) {
            LOG.error("Lamp is null. Cannot turn on lamp.");
        } else {
            LOG.info("Lamp was turned on");
            lamp.setState(new ActiveState(lamp));
            simulationFacade.addDeviceEventsTypeToEventsHub(lamp, EventsType.Turn_on_device);
            human.countDeviceUsage(lamp);
        }
    }

    /**
     * Get lamp
     * @param id unique id of one specific lamp
     * @return get lamp by id
     */

    private Lamp getLampById(Integer id) {
        LOG.info("Trying to get Lamp by Id: " + id);
        return lamps.stream()
                .filter(l -> l.getId() == id)
                .findAny().orElse(null);
    }
}
