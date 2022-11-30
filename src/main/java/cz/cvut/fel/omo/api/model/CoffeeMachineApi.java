package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CoffeeMachineApi {
    private static final Logger LOG = Logger.getLogger(TVApi.class.getSimpleName());
    
    public void makeCoffee(List<Integer> cup, int MmOfMilk, int MmOfWater, int amountOfBeans) {
        cup.add(MmOfMilk);
        cup.add(MmOfWater);
        cup.add(amountOfBeans);
        LOG.info("Your coffee is ready!");

    }
}
