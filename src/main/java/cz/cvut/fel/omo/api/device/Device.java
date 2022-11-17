package cz.cvut.fel.omo.api.device;

import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Device {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());


    private String name;


}
