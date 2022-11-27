package cz.cvut.fel.omo.patterns.builder;

import cz.cvut.fel.omo.api.user.Pet;
import cz.cvut.fel.omo.api.user.PetType;
import cz.cvut.fel.omo.api.user.ResidentPermission;

import java.util.Set;
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

    public PetBuilder setPermissions(ResidentPermission permissions) {
        this.permissions = permissions;
        return this;
    }

    public PetBuilder setPetType(PetType petType) {
        this.petType = petType;
        return this;
    }

    public Pet build() {
        Pet pet = new Pet();

        if (name.isEmpty() || petType == null || permissions == null) {
            LOGGER.error("Pet attributes are empty!");
        }

        return pet;
    }
}
