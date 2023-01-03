package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.observer.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>This class describes Manuals, which user is using when he needs to repair broken device</p>
 */
public class Manual implements Subject {
    private String content;
    private Integer deviceId;
    private final Set<Observer> observers = new HashSet<>();
    private Boolean isRead;


    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public void readManual(){
        setRead(true);
    }



    public Manual(String content, Integer deviceId) {
        this.content = content;
        this.deviceId = deviceId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifySubscribers(EventsType eventsType, SimulationFacade simulationFacade) {
        for (Observer observer : observers) {
            observer.update(eventsType, simulationFacade);
        }
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}
