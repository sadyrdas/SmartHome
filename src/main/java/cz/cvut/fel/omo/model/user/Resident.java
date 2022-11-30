package cz.cvut.fel.omo.model.user;

import cz.cvut.fel.omo.patterns.observer.Observer;

public abstract class Resident implements Observer {

    private String name;

    private ResidentPermission permission;

    public Resident(String name){
        this.name = name;
    }

    public Resident (String name, ResidentPermission residentPermission) {
        this.name = name;
        this.permission = residentPermission;
    }

    public Resident() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResidentPermission getPermissions() {
        return permission;
    }

    public void setPermissions(ResidentPermission permission) {
        this.permission = permission;
    }
}
