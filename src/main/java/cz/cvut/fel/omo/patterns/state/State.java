package cz.cvut.fel.omo.patterns.state;

import java.util.logging.Logger;

public interface State {
    Logger LOG = Logger.getLogger(State.class.getSimpleName());

    void setPower();
}
