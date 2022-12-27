package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.proxy.ProxyAccess;
import cz.cvut.fel.omo.simulation.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class TransportApi {

    public TransportApi() {

    }

    public void removeHuman(Transport transport, Human human) {
        Objects.requireNonNull(human);
        Objects.requireNonNull(transport);
        transport.removeHuman(human);
    }

    public void accessTransport(Human human, Transport transport, ProxyAccess proxyAccess) {
        if (proxyAccess.accessAndDriveCar(human, transport)) {
            transport.addHuman(human);
        }
    }
}
