package cz.cvut.fel.omo.patterns.facade;

import cz.cvut.fel.omo.model.device.*;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.house.Floor;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimulationFacade {

    private static final Logger LOGGER = LogManager.getLogger(SimulationFacade.class.getName());

    public void addAllSubscribers(House house) {
        for (Sensor sensor : house.getSensors()) {
            for (Device device : house.getDevices()) {
                sensor.addSubscriber(device);
            }
            for (Human human : house.getHumans()) {
                sensor.addSubscriber(human);
            }
            for (Pet pet : house.getPets()) {
                sensor.addSubscriber(pet);
            }
            for (Floor f : house.getFloors()) {
                for (Room r : f.getRooms()) {
                    for (Window w : r.getWindows()) {
                        sensor.addSubscriber(w);
                    }
                }
            }
        }

        for(Device coffeeMachine : house.getListOfSpecificDevicesByName("CoffeeMachine")) {
            for (Human h : house.getAllAdults()) {
                ((CoffeeMachine) coffeeMachine).addSubscriber(h);
            }
        }

        for(Device fridge : house.getListOfSpecificDevicesByName("Fridge")) {
            for (Human h : house.getAllAdults()) {
                ((Fridge) fridge).addSubscriber(h);
            }
        }

        for(Device feederForPet : house.getListOfSpecificDevicesByName("FeederForPet")) {
            for (Human h : house.getAllAdults()) {
                ((FeederForPet) feederForPet).addSubscriber(h);
            }
        }

        for(Device lamp : house.getListOfSpecificDevicesByName("Lamp")) {
            for (Human h : house.getAllAdults()) {
                ((Lamp) lamp).addSubscriber(h);
            }
        }

        for(Device shower : house.getListOfSpecificDevicesByName("Shower")) {
            for (Human h : house.getAllAdults()) {
                ((Shower) shower).addSubscriber(h);
            }
        }
    }

}
