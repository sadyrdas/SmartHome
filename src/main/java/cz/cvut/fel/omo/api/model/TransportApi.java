package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.proxy.ProxyAccess;

import java.util.Objects;

/**
 * <p>Provides API for transport which includes access all transport in house.</p>
 */

public class TransportApi {

    private final SimulationFacade simulationFacade;

    /**
     * Main constructor
     *
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     */
    public TransportApi(SimulationFacade simulationFacade) {

        this.simulationFacade = simulationFacade;
    }

    /**
     * Remove human from transport
     *
     * @param transport - is the one transport
     * @param human     - is the one user, who we remove from transport
     */

    public void removeHuman(Transport transport, Human human) {
        Objects.requireNonNull(human);
        Objects.requireNonNull(transport);
        transport.removeHuman(human);
        simulationFacade.addHumanEventToEventsHub(human, ActivityUser.OFF_TRANSPORT);
    }

    /**
     * Add human to transport
     *
     * @param human       - is the one user who uses transport
     * @param transport   - is the one transport, which is used by human
     * @param proxyAccess - pattern, which determines what is the User Permission, every logic is working in SimulationFacade.java
     */

    public void accessTransport(Human human, Transport transport, ProxyAccess proxyAccess) {
        if (proxyAccess.accessAndDriveCar(human, transport)) {
            simulationFacade.addHumanEventToEventsHub(human, ActivityUser.USE_TRANSPORT);
            transport.addHuman(human);
        }
    }
}
