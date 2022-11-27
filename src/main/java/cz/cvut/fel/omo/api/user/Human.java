package cz.cvut.fel.omo.api.user;

public class Human extends Resident{
    public Human(String name) {
        super(name);
    }

    public Human(String name, ResidentPermission permission) {
        super(name, permission);
    }

    public Human() {
        super();
    }

}
