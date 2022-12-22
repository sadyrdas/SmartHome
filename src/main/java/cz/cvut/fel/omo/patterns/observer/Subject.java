package cz.cvut.fel.omo.patterns.observer;

import cz.cvut.fel.omo.model.events.EventsType;

import java.time.LocalDateTime;

public interface Subject {
    void addSubscriber(Observer observer);

    void notifySubscribers(EventsType eventsType);
}
