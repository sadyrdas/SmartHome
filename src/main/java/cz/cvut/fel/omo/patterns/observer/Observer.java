package cz.cvut.fel.omo.patterns.observer;

import cz.cvut.fel.omo.model.events.EventsType;

public interface Observer {
    void update(EventsType events_type);
}
