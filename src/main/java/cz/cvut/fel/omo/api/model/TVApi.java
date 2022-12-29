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

public class TVApi {
    private static final Logger LOG = LogManager.getLogger(TVApi.class.getName());
    private final Set<TV> tvs;
    private final SimulationFacade simulationFacade;


    public TVApi(Set<TV> tvs, SimulationFacade simulationFacade) {
        this.tvs = tvs;
        this.simulationFacade = simulationFacade;
    }

    public void turnOffTvById( Integer id) {
        TV tv = getTvById(id);
        Objects.requireNonNull(tv);

        tv.setState(new StoppedState(tv));
        LOG.info("TV with id: " + id + " was turned off!");
        simulationFacade.addDeviceEventsTypeToEventsHub(tv, EventsType.Turn_off_device);
    }

    public void turnOnTvById(Human human, Integer id) {
        TV tv = getTvById(id);
        Objects.requireNonNull(tv);

        tv.setState(new ActiveState(tv));
        LOG.info("TV with id: " + id + " was turned on!");
        simulationFacade.addDeviceEventsTypeToEventsHub(tv, EventsType.Turn_on_device);
        simulationFacade.addHumanEventToEventsHub(human, ActivityUser.WATCH_TV);
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
