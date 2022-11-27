package cz.cvut.fel.omo.patterns.state;

import cz.cvut.fel.omo.api.room.Room;

import java.util.logging.Logger;

public interface State {
    Logger LOG = Logger.getLogger(State.class.getSimpleName());

    void setPower();
}
