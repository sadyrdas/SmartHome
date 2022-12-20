package cz.cvut.fel.omo.model.transport;

import cz.cvut.fel.omo.model.user.Human;

import java.util.LinkedList;
import java.util.Queue;

public class Transport {
    private CategoryTransport categoryTransport;
    private String name;
    private int amount;
    private Human currentPerson;
    private Queue<Human> queueForAccess = new LinkedList<>();

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

    public void accessTransport(Human person) {
        if (queueForAccess.isEmpty()) {
            currentPerson = person;
        }
        queueForAccess.add(person);
    }

//    public void leaveTransport() {
//        queueForAccess.
//    }
}
