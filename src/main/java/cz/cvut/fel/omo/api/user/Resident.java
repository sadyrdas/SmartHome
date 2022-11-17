package cz.cvut.fel.omo.api.user;

import java.util.Set;

public abstract class Resident {

    private String name;

    private Set<ResidentPermission> permissions;

    public Resident(String name){
        this.name = name;
    }

    public Resident() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ResidentPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<ResidentPermission> permissions) {
        this.permissions = permissions;
    }
}
