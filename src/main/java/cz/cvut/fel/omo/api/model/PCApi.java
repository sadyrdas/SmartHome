package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.PC;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.ActivityPet;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.Objects;
import java.util.Set;

public class PCApi {
    private final Set<PC> pcs;
    private final SimulationFacade simulationFacade;

    public PCApi(Set<PC> pcs, SimulationFacade simulationFacade) {
        this.pcs = pcs;
        this.simulationFacade = simulationFacade;
    }

    public void turnOffPCById(Human human, Integer id) {
        PC pc = getPcById(id);
        Objects.requireNonNull(pc);

        pc.setState(new StoppedState(pc));
        simulationFacade.addDeviceEventsTypeToEventsHub(pc, EventsType.Turn_off_device);
        human.countDeviceUsage(pc);
    }

    public void turnOnPCById(Human human, Integer id) {
        PC pc = getPcById(id);
        Objects.requireNonNull(pc);

        pc.setState(new ActiveState(pc));
        simulationFacade.addDeviceEventsTypeToEventsHub(pc, EventsType.Turn_on_device);
        simulationFacade.addHumanEventToEventsHub(human, ActivityUser.PLAYING_PC);
        human.countDeviceUsage(pc);
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
