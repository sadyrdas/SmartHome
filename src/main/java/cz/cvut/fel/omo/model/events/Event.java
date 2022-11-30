package cz.cvut.fel.omo.model.events;

public class Event {
    private Events_Type event_type;

    public Event(Events_Type event_type) {
        this.event_type = event_type;
    }

    public Events_Type getEvent_type() {
        return event_type;
    }

    public void setEvent_type(Events_Type event_type) {
        this.event_type = event_type;
    }
}
