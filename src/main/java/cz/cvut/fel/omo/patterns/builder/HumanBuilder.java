package cz.cvut.fel.omo.patterns.builder;

import cz.cvut.fel.omo.api.user.Human;
import cz.cvut.fel.omo.api.user.ResidentPermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;


// https://refactoring.guru/design-patterns/builder/java/example

public class HumanBuilder {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private String name;
    private Set<ResidentPermission> permissions;

    public HumanBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HumanBuilder setPermissions(Set<ResidentPermission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public Human build() {
        Human human = new Human();

        if (name.isEmpty() || permissions.isEmpty()) {
            LOGGER.error("Pet attributes are empty!");
        }

        return human;
    }
}
