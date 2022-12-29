package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.api.model.FeederForPetApi;
import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.FeederForPet;
import cz.cvut.fel.omo.model.device.Fridge;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Human extends Resident {
    private static final Logger LOG = LogManager.getLogger(Human.class.getName());
    private ActivityUser activityUser;
    private final Map<Device, Integer> deviceUsageCount = new HashMap<>();
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
    public void update(EventsType events_type, SimulationFacade simulationFacade) {
        switch (events_type) {
            case Smoky -> setActivityUser(ActivityUser.NOT_AT_HOME);
            case Cold_temperature -> setActivityUser(ActivityUser.OPEN_WINDOW);
            case Repair_device -> setActivityUser(ActivityUser.REPAIR);
            case Empty_fridge -> ((Fridge) simulationFacade.getHouse().getOneDevice("Fridge")).addFoodToFridge();
            case Empty_FeederForFood ->{
                ((FeederForPet) simulationFacade.getHouse().getOneDevice("FeederForPet")).fillFeeder();
                LOG.info("Human "+ getName() + " filled feeder");
            }
            case Broken_device -> setActivityUser(ActivityUser.READ_MANUAL);
        }

    }

    public ActivityUser getActivityUser() {
        return activityUser;
    }

    public void setActivityUser(ActivityUser activityUser) {
        this.activityUser = activityUser;
    }

    public void countDeviceUsage(Device device) {
        this.deviceUsageCount.merge(device, 1, Integer::sum);
    }

    public Map<Device, Integer> getDeviceUsageCount() {
        return deviceUsageCount;
    }
}
