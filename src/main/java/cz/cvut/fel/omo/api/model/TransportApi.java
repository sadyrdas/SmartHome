package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.proxy.ProxyAccess;

import java.util.Objects;

public class TransportApi {

    private final SimulationFacade simulationFacade;
    public TransportApi(SimulationFacade simulationFacade) {

        this.simulationFacade = simulationFacade;
    }

    public void removeHuman(Transport transport, Human human) {
        Objects.requireNonNull(human);
        Objects.requireNonNull(transport);
        transport.removeHuman(human);
        simulationFacade.addHumanEventToEventsHub(human, ActivityUser.OFF_TRANSPORT);
    }

    public void accessTransport(Human human, Transport transport, ProxyAccess proxyAccess) {
        if (proxyAccess.accessAndDriveCar(human, transport)) {
            simulationFacade.addHumanEventToEventsHub(human, ActivityUser.USE_TRANSPORT);
            transport.addHuman(human);
        }
    }
}
