package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.TV;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Set;

/**
 * <p>Provides API for TV which includes access TVs in house.</p>
 */

public class TVApi {
    private static final Logger LOG = LogManager.getLogger(TVApi.class.getName());
    private final Set<TV> tvs;
    private final SimulationFacade simulationFacade;

    /**
     * Main constructor
     *
     * @param tvs              - set of tv in house
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     */
    public TVApi(Set<TV> tvs, SimulationFacade simulationFacade) {
        this.tvs = tvs;
        this.simulationFacade = simulationFacade;
    }

    /**
     * Turn off - TV . Set state of device to Stopped.
     *
     * @param human the one user who does the action
     * @param id    unique id of one specific air conditioner
     */

    public void turnOffTvById(Human human, Integer id) {
        TV tv = getTvById(id);
        Objects.requireNonNull(tv);

        tv.setState(new StoppedState(tv));
        LOG.info("TV with id: " + id + " was turned off!");
        simulationFacade.addDeviceEventsTypeToEventsHub(tv, EventsType.Turn_off_device);
        human.countDeviceUsage(tv);
    }

    /**
     * Turn on- TV . Set state of device to Active.
     *
     * @param human the one user who does the action
     * @param id    unique id of one specific air conditioner
     */

    public void turnOnTvById(Human human, Integer id) {
        TV tv = getTvById(id);
        Objects.requireNonNull(tv);

        tv.setState(new ActiveState(tv));
        LOG.info("TV with id: " + id + " was turned on!");
        simulationFacade.addDeviceEventsTypeToEventsHub(tv, EventsType.Turn_on_device);
        simulationFacade.addHumanEventToEventsHub(human, ActivityUser.WATCH_TV);
        human.countDeviceUsage(tv);
    }

    private TV getTvById(Integer id) {
        for (TV tv : tvs) {
            if (tv.getId() == id) {
                return tv;
            }
        }
        return null;
    }

    public Set<TV> getTvs() {
        return tvs;
    }
}
