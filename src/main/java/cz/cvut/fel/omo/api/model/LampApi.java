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

public class LampApi {
    private static final Logger LOG = LogManager.getLogger(LampApi.class.getSimpleName());
    private final SimulationFacade simulationFacade;

    public Set<Lamp> getLamps() {
        return lamps;
    }

    private final Set<Lamp> lamps;

    public LampApi(SimulationFacade simulationFacade, Set<Lamp> lamps) {
        this.simulationFacade = simulationFacade;
        this.lamps = lamps;
    }

    public void turnOffLamp(Integer id) {
        Lamp lamp = getLampById(id);
        if (lamp == null) {
            LOG.error("Lamp is null. Cannot turn off lamp.");
            return;
        } else {
            LOG.info("Lamp was turned off");
            lamp.setState(new StoppedState(lamp));
            simulationFacade.addDeviceEventsTypeToEventsHub(lamp, EventsType.Turn_off_device);
        }
    }

    public void turnOnLamp(Integer id) {
        Lamp lamp = getLampById(id);

        if (lamp == null) {
            LOG.error("Lamp is null. Cannot turn on lamp.");
            return;
        } else {
            LOG.info("Lamp was turned on");
            lamp.setState(new ActiveState(lamp));
            simulationFacade.addDeviceEventsTypeToEventsHub(lamp, EventsType.Turn_on_device);
        }
    }

    private Lamp getLampById(Integer id) {
        LOG.info("Trying to get Lamp by Id: " + id);
        return lamps.stream()
                .filter(l -> l.getId() == id)
                .findAny().orElse(null);
    }
}
