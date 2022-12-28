package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.TV;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Set;

public class TVApi {
    private static final Logger LOG = LogManager.getLogger(TVApi.class.getName());
    private final Set<TV> tvs;


    public TVApi(Set<TV> tvs) {
        this.tvs = tvs;
    }

    public void turnOffTvById(Integer id) {
        TV tv = getTvById(id);
        Objects.requireNonNull(tv);

        tv.setState(new StoppedState(tv));
        LOG.info("TV with id: " + id + " was turned off!");
    }

    public void turnOnTvById(Integer id) {
        TV tv = getTvById(id);
        Objects.requireNonNull(tv);

        tv.setState(new ActiveState(tv));
        LOG.info("TV with id: " + id + " was turned on!");
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
