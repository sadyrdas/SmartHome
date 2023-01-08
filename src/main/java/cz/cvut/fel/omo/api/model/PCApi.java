package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.PC;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.Objects;
import java.util.Set;

/**
 * <p>Provides API for PC which includes access all PC in house.</p>
 */
public class PCApi {
    private final Set<PC> pcs;
    private final SimulationFacade simulationFacade;

    /**
     * Main constructor
     *
     * @param pcs              set of pcs in house
     * @param simulationFacade Facade Design pattern to hide simulation complexity behind a simple class
     */
    public PCApi(Set<PC> pcs, SimulationFacade simulationFacade) {
        this.pcs = pcs;
        this.simulationFacade = simulationFacade;
    }

    /**
     * Turn off PC. Set state of device to Stopped.
     *
     * @param human the one user who does the action
     * @param id    unique id of one specific PC
     */

    public void turnOffPCById(Human human, Integer id) {
        PC pc = getPcById(id);
        Objects.requireNonNull(pc);

        pc.setState(new StoppedState(pc));
        simulationFacade.addDeviceEventsTypeToEventsHub(pc, EventsType.Turn_off_device);
        human.countDeviceUsage(pc);
    }

    /**
     * Turn on PC. Set state of device to Active.
     *
     * @param human the one user who does the action
     * @param id    unique id of one specific PC
     */

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
