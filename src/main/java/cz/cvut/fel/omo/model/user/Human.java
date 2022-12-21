package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.model.events.EventsType;

public class Human extends Resident{
    private ActivityUser activityUser;
    public Human(String name) {
        super(name);
    }

    public Human(String name, ResidentPermission permission) {
        super(name, permission);
    }

    public Human() {
        super();
    }

    @Override
    public void update(EventsType events_type) {
    }

    public ActivityUser getActivityUser() {
        return activityUser;
    }

    public void setActivityUser(ActivityUser activityUser) {
        this.activityUser = activityUser;
    }
}
