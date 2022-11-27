package cz.cvut.fel.omo.api.user;

public class Pet extends Resident{

    private final ResidentPermission permission = ResidentPermission.PET;

    public Pet(String name) {
        super(name);
    }

    public Pet() {
        super();
    }

    public ResidentPermission getPermission() {
        return permission;
    }
}
