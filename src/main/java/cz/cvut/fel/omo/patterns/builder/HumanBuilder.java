package cz.cvut.fel.omo.patterns.builder;

import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * <p>This class implements Design Patter Builder for our example for Human
 * https://refactoring.guru/design-patterns/builder</p>
 */

public class HumanBuilder {
    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());

    private String name;
    private ResidentPermission permissions;

    public HumanBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public HumanBuilder setPermissions(String permissions) {
        this.permissions = getPermissions(permissions);
        return this;
    }

    private ResidentPermission getPermissions(String permissions){
        return ResidentPermission.valueOf(permissions);
    }



    public Human build() {
        Human human = new Human();

        if (name.isEmpty()) {
            LOGGER.error("Human attributes are empty!");
            // TODO can we do better than just return null?
            return null;
        } else {
            return new Human(this.name, this.permissions);
        }
    }
}
