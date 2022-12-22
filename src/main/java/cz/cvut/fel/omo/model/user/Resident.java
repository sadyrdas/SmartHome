package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.patterns.observer.Observer;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.logging.Logger;

public abstract class Resident implements Observer {

    private static final Logger LOG = Logger.getLogger(Device.class.getSimpleName());

    private String name;

    private ResidentPermission permission;
    private ActivityUser activityUser;

    public Resident(String name){
        this.name = name;
    }

    public Resident (String name, ResidentPermission residentPermission) {
        this.name = name;
        this.permission = residentPermission;
    }

    @Override
    public void update(EventsType eventsType){
        switch (eventsType) {
            case Smoky -> {
                setActivityUser(ActivityUser.RUN);
                LOG.info("WE ARE ON FIRE-RUN");
            }
            case Cold_temperature ->{
                setActivityUser(ActivityUser.CLOSE_WINDOW);
                LOG.info("It is so cold- CLOSE WINDOWS");
            }
            case Hot_temperature -> {
                setActivityUser(ActivityUser.OPEN_WINDOW);
                LOG.info("It is so hot- OPEN WINDOWS");}
        }

    }

    public Resident() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResidentPermission getPermissions() {
        return permission;
    }

    public void setPermissions(ResidentPermission permission) {
        this.permission = permission;
    }

    public ActivityUser getActivityUser() {
        return activityUser;
    }

    public void setActivityUser(ActivityUser activityUser) {
        this.activityUser = activityUser;
    }
}
