package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.CoffeeMachine;
import cz.cvut.fel.omo.model.events.EventsType;

import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoffeeMachineApi {
    private static final Logger LOG = LogManager.getLogger(CoffeeMachineApi.class.getName());
    private final CoffeeMachine coffeeMachine;
    private final SimulationFacade simulationFacade;

    public int getMlOfMilk() {
        return coffeeMachine.getMlOfMilk();
    }

    public void setMlOfMilk(int mlOfMilk) {
        coffeeMachine.setMlOfMilk(mlOfMilk);
    }

    public int getMlOfWater() {
        return coffeeMachine.getMlOfWater();
    }

    public void setMlOfWater(int mlOfWater) {
        coffeeMachine.setMlOfWater(mlOfWater);
    }

    public int getAmountOfBeans() {
        return coffeeMachine.getAmountOfBeans();
    }

    public void setAmountOfBeans(int amountOfBeans) {
        coffeeMachine.setAmountOfBeans(amountOfBeans);
    }

    public CoffeeMachineApi(CoffeeMachine coffeeMachine, SimulationFacade simulationFacade) {
        this.coffeeMachine = coffeeMachine;
        this.simulationFacade = simulationFacade;
    }

    public void makeCoffee (Human human) {
        if (coffeeMachine.getMlOfMilk() - 100 < 0 ||
                coffeeMachine.getMlOfWater() - 150 < 0  ||
                coffeeMachine.getAmountOfBeans() - 1 < 0) {
            coffeeMachine.notifySubscribers(EventsType.Empty_CoffeeMachine, simulationFacade);
            LOG.warn("Ingredients for coffee is below zero " + "Ask mother to full");
            simulationFacade.addDeviceEventsTypeToEventsHub(coffeeMachine, EventsType.Empty_CoffeeMachine);
            simulationFacade.addHumanEventToEventsHub(human, ActivityUser.FILL_COFFEE_MACHINE);
        } else {
            simulationFacade.addDeviceEventsTypeToEventsHub(coffeeMachine, EventsType.Turn_on_device);
            coffeeMachine.setState(new ActiveState(coffeeMachine));

            coffeeMachine.setMlOfMilk(coffeeMachine.getMlOfMilk() - 100);
            coffeeMachine.setMlOfWater(coffeeMachine.getMlOfWater() - 100);
            coffeeMachine.setAmountOfBeans(coffeeMachine.getAmountOfBeans() - 1);

            LOG.info("Your coffee is ready! Remaining amount of milk: " + coffeeMachine.getMlOfMilk() +
                    " ml., water: " + coffeeMachine.getMlOfWater() + " ml., " +
                    coffeeMachine.getAmountOfBeans() + " beans");
            simulationFacade.addHumanEventToEventsHub(human, ActivityUser.DRINK_COFFEE);
            coffeeMachine.setState(new StoppedState(coffeeMachine));
            human.countDeviceUsage(coffeeMachine);

        }
    }

    public void fillCoffeeMachine() {
        coffeeMachine.setAmountOfBeans(100);
        coffeeMachine.setMlOfMilk(100);
        coffeeMachine.setMlOfWater(100);
        LOG.info("Coffee machine was filled. Current amount of milk: " + coffeeMachine.getMlOfMilk() +
                " ml., " + coffeeMachine.getMlOfWater() + " ml., beans: " + coffeeMachine.getAmountOfBeans() + ".");
    }

}
