package cz.cvut.fel.omo.model.events;

import java.time.LocalDateTime;

public class Event {
    private EventsType event_type;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(EventsType event_type, LocalDateTime startTime, LocalDateTime endTime) {
        this.event_type = event_type;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public EventsType getEvent_type() {
        return event_type;
    }

    public void setEvent_type(EventsType event_type) {
        this.event_type = event_type;
    }
}
