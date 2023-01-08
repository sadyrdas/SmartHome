package cz.cvut.fel.omo.model.events;

/**
 * <p>This is the main class of events in house.</p>
 */
public class Event {
    private EventsType event_type;


    public Event(EventsType event_type) {
        this.event_type = event_type;
    }

    public EventsType getEvent_type() {
        return event_type;
    }

    public void setEvent_type(EventsType event_type) {
        this.event_type = event_type;
    }
}
