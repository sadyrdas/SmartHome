package cz.cvut.fel.omo.api.user;

import java.util.Set;

public abstract class Resident {

    private String name;

    private Set<ResidentPermission> permissions;

    public Resident(String name){
        this.name = name;
    }



}
