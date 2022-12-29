package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;

import java.util.*;

public class Pet extends Resident{

    private final ResidentPermission permission = ResidentPermission.PET;
    private PetType petType;

    private ActivityPet activityPet;
    private final Map<Device, Integer> deviceUsageCount = new HashMap<>();

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


    public void countDeviceUsage(Device device) {
        this.deviceUsageCount.merge(device, 1, Integer::sum);
    }

    public Map<Device, Integer> getDeviceUsageCount() {
        return deviceUsageCount;
    }
}
