package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pet extends Resident{

    private final ResidentPermission permission = ResidentPermission.PET;
    private PetType petType;

    private ActivityPet activityPet;






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
    public void update(EventsType events_type, SimulationFacade simulationFacade) {

    }

    public PetType getPetType() {
        return petType;
    }


    public ActivityPet getActivityPet() {
        return activityPet;
    }

    public void setActivityPet(ActivityPet activityPet) {
        this.activityPet = activityPet;
    }
}
