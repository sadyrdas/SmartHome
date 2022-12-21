package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.model.events.EventsType;

public class Pet extends Resident{

    private final ResidentPermission permission = ResidentPermission.PET;
    private PetType petType;

    private ActivityPet activityPet;

    public ActivityPet getActivityPet() {
        return activityPet;
    }

    public void setActivityPet(ActivityPet activityPet) {
        this.activityPet = activityPet;
    }




    public Pet(String name, PetType petType) {
        super(name);
        this.petType = petType;
    }

    public Pet() {
        super();
    }

    public ResidentPermission getPermission() {
        return permission;
    }
    @Override
    public void update(EventsType events_type) {

    }

    public PetType getPetType() {
        return petType;
    }



}
