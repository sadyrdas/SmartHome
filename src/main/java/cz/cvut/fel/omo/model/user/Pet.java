package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.model.events.Events_Type;

public class Pet extends Resident{

    private final ResidentPermission permission = ResidentPermission.PET;

    public Pet(String name) {
        super(name);
    }

    public Pet() {
        super();
    }

    public ResidentPermission getPermission() {
        return permission;
    }

    @Override
    public void update(Events_Type events_type) {

    }
}
