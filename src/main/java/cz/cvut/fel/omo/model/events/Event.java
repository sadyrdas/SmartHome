package cz.cvut.fel.omo.model.events;

import java.time.LocalDateTime;

public class Event {
    private EventsType event_type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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
