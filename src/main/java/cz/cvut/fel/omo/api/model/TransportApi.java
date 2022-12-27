package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.transport.CategoryTransport;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;

public class TransportApi {

    private static final Logger LOG = Logger.getLogger(TransportApi.class.getName());

    private Set<Human> users;
    private CategoryTransport categoryTransports;
    private Set<Transport> transports;
    private ResidentPermission residentPermission;
    private Human currentPerson;
    private Set<Transport> transport;
    private Queue<Human> queueForAccess = new LinkedList<>();

    public TransportApi(Set<Transport> transport){
        this.transport = transport;
    }

    public void removeHuman(Human human){
        Objects.requireNonNull(human);
        users.remove(human);
    }

    public void accessTransport(Human person, ResidentPermission residentPermission, CategoryTransport categoryTransports) {
        if (queueForAccess.isEmpty()) {
            currentPerson = person;
        }
        if (categoryTransports == CategoryTransport.BIKE){
            queueForAccess.add(person);
            LOG.info("Little " + residentPermission + " is taking a bike");
        }
        if (categoryTransports == CategoryTransport.CAR) {
            queueForAccess.add(person);
            LOG.info("Person " + residentPermission + " is taking a car");
        }
    }

}
