package cz.cvut.fel.omo.model.transport;

import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.simulation.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class Transport {
    private CategoryTransport categoryTransport;
    private String name;
    private int amount;
    private Human currentHuman;
    private Queue<Human> queueForAccess = new LinkedList<>();

    private static final Logger LOG = LogManager.getLogger(Transport.class.getName());

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

    public void setCurrentHuman(Human currentHuman) {
        this.currentHuman = currentHuman;
    }

    public Human getCurrentHuman() {
        return currentHuman;
    }

    public void addHuman(Human human) {
        if (queueForAccess.size() == 0) {
            currentHuman = human;
        }
        queueForAccess.add(human);
    }

    public void removeHuman(Human human) {
        if (queueForAccess.isEmpty()) {
            LOG.info("Queue for accessing transport: " + getName() + " is empty.");
            return;
        }

        if (currentHuman == human) {
            LOG.info("Human using transport " + this.getName() + " is no longer use this transport." +
                    this.getName() + " is free to use!");
            currentHuman = null;
        }

        LOG.info("Human was removed from waiting queue for transport: " + getName());
        queueForAccess.remove();
    }
}
