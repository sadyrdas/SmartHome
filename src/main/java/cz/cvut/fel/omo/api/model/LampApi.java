package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Lamp;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class LampApi {
    private static final Logger LOG = LogManager.getLogger(LampApi.class.getSimpleName());
    private Set<Lamp> lamps = new HashSet<>();

    public LampApi(Set<Lamp> lamps) {
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
        }
    }

    private Lamp getLampById(Integer id) {
        LOG.info("Trying to get Lamp by Id: " + id);
        return lamps.stream()
                .filter(l -> l.getId() == id)
                .findAny().orElse(null);
    }
}
