package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.PC;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

public class PCApi {
    private final PC pc;

    public PCApi(PC pc) {
        this.pc = pc;
    }

    public void turnOfPC() {
        pc.setState(new StoppedState(this.pc));
    }

    public void turnOnPC() {
        pc.setState(new ActiveState(this.pc));
    }

    public PC getPc() {
        return pc;
    }
}
