package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.PC;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.Objects;
import java.util.Set;

public class PCApi {
    private final Set<PC> pcs;

    public PCApi(Set<PC> pcs) {
        this.pcs = pcs;
    }

    public void turnOffPCById(Integer id) {
        PC pc = getPcById(id);
        Objects.requireNonNull(pc);

        pc.setState(new StoppedState(pc));
    }

    public void turnOnPCById(Integer id) {
        PC pc = getPcById(id);
        Objects.requireNonNull(pc);

        pc.setState(new ActiveState(pc));
    }

    private PC getPcById(Integer id) {
        for (PC pc : pcs) {
            if (pc.getId() == id) {
                return pc;
            }
        }
        return null;
    }

    public Set<PC> getPcs() {
        return pcs;
    }
}
