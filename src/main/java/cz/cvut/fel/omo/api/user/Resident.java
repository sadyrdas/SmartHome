package cz.cvut.fel.omo.api.user;

import java.util.Set;

public abstract class Resident {

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
