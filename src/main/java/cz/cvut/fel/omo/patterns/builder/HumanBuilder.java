package cz.cvut.fel.omo.patterns.builder;

import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// https://refactoring.guru/design-patterns/builder/java/example

public class HumanBuilder {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private String name;
    private ResidentPermission permissions;

    public HumanBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HumanBuilder setPermissions(ResidentPermission permissions) {
        this.permissions = permissions;
        return this;
    }

    public Human build() {
        Human human = new Human();

        if (name.isEmpty()) {
            LOGGER.error("Pet attributes are empty!");
            // TODO can we do better than just return null?
            return null;
        }

        return human;
    }
}
