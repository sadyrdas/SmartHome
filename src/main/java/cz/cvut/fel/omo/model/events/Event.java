package cz.cvut.fel.omo.model.events;

import java.time.LocalDateTime;

public class Event {
    private EventsType event_type;
    private final int startTime;
    private final int endTime;

    public Event(EventsType event_type, int startTime, int endTime) {
        this.event_type = event_type;
        this.startTime = 0;
        this.endTime = 24;
    }

    public EventsType getEvent_type() {
        return event_type;
    }

    public void setEvent_type(EventsType event_type) {
        this.event_type = event_type;
    }
}
