package cz.cvut.fel.omo.model.transport;

import cz.cvut.fel.omo.api.model.TransportApi;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class Transport {
    private CategoryTransport categoryTransport;
    private String name;
    private int amount;
    private Human currentPerson;
    private Queue<Human> queueForAccess = new LinkedList<>();
    private static final Logger LOG = Logger.getLogger(Transport.class.getName());

    public Transport(CategoryTransport categoryTransport, String name, int amount) {
        this.categoryTransport = categoryTransport;
        this.name = name;
        this.amount = amount;
    }

    public CategoryTransport getCategoryTransport() {
        return categoryTransport;
    }

    public void setCategoryTransport(CategoryTransport categoryTransport) {
        this.categoryTransport = categoryTransport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCurrentPerson(Human currentPerson) {
        this.currentPerson = currentPerson;
    }

    public Human getCurrentPerson() {
        return currentPerson;
    }


    public void accessTransport(Human person, ResidentPermission residentPermission) {
        if (queueForAccess.isEmpty()) {
            currentPerson = person;
        }
        if (categoryTransport == CategoryTransport.BIKE){
            queueForAccess.add(person);
            LOG.info("Little " + residentPermission + " is taking a bike");
        }
        if (categoryTransport == CategoryTransport.CAR) {
            queueForAccess.add(person);
            LOG.info("Person " + residentPermission + " is taking a car");
        }
        if (categoryTransport == CategoryTransport.SKI) {
            queueForAccess.add(person);
            LOG.info("Person" + residentPermission + " is taking a skies");
        }
    }


//    public void leaveTransport() {
//        queueForAccess.
//    }
}
