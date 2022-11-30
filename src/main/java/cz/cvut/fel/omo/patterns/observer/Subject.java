package cz.cvut.fel.omo.patterns.observer;

import java.time.LocalDateTime;

public interface Subject {
    void addSubscriber(Observer observer);

    void notifySubscribers();
}
