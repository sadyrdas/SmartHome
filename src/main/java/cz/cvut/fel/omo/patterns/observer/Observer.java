package cz.cvut.fel.omo.patterns.observer;

import cz.cvut.fel.omo.model.events.Events_Type;

public interface Observer {
    void update(Events_Type events_type);
}
