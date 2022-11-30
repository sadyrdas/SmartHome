package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.model.events.Events_Type;

public class Human extends Resident{
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
    public void update(Events_Type events_type) {
    }
}
