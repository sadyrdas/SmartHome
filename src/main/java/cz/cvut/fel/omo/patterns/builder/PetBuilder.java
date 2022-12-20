package cz.cvut.fel.omo.patterns.builder;

import cz.cvut.fel.omo.model.user.Pet;
import cz.cvut.fel.omo.model.user.PetType;
import cz.cvut.fel.omo.model.user.ResidentPermission;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PetBuilder {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private String name;
    private ResidentPermission permissions;

    private PetType petType;

    public PetBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder setPermissions(String permissions) {
        this.permissions = getPermissions(permissions);
        return this;
    }

    private ResidentPermission getPermissions(String permissions){
        return ResidentPermission.valueOf(permissions);
    }

    public PetBuilder setPetType(String petType) {
        this.petType = getPetType(petType);
        return this;
    }

    private PetType getPetType(String type) {
        return switch (type) {
            case "Dog" -> PetType.Dog;
            case "Cat" -> PetType.Cat;
            case "Parrot" -> PetType.Parrot;
            default -> null;
        };
    }

    public Pet build() {

        if (name.isEmpty() || petType == null || permissions == null) {
            LOGGER.error("Pet attributes are empty!");
            return null;
        } else {
            return new Pet(this.name, this.petType);
        }

    }
}
