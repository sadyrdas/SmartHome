package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Shower;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class ShowerApi {
    private final Shower shower;

    public ShowerApi(Shower shower) {
        this.shower = shower;
    }

    public void turnOffShower() {
        shower.setState(new StoppedState(this.shower));
    }

    public void turnOnShower() {
        shower.setState(new ActiveState(this.shower));
    }

    public Shower getShower() {
        return shower;
    }
}
