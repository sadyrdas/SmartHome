package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Shower;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

/**
 * <p>Provides API for shower which includes access shower in house.</p>
 */

public class ShowerApi {
    private final Shower shower;

    /**
     * Main constructor
     * @param shower is the shower in house
     */
    public ShowerApi(Shower shower) {
        this.shower = shower;
    }

    /**
     * Turn off Shower. Set state of device to Stopped.
     * @param human - is the one user who does the action
     */

    public void turnOffShower(Human human) {
        shower.setState(new StoppedState(this.shower));
        human.countDeviceUsage(shower);
    }

    /**
     * Turn on Shower. Set state of device to Active
     * @param human - is the one user who does the action
     */

    public void turnOnShower(Human human) {
        shower.setState(new ActiveState(this.shower));
        human.countDeviceUsage(shower);
    }

    public Shower getShower() {
        return shower;
    }
}
