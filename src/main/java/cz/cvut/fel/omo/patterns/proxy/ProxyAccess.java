package cz.cvut.fel.omo.patterns.proxy;

import cz.cvut.fel.omo.api.model.CoffeeMachineApi;
import cz.cvut.fel.omo.api.model.MusicCenterAPI;
import cz.cvut.fel.omo.model.transport.CategoryTransport;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProxyAccess {

    private static final Logger LOG = LogManager.getLogger(ProxyAccess.class.getName());

    public Boolean accessAndDriveCar(Human person, Transport transport) {
        ResidentPermission allowedResidentPermission = ResidentPermission.ADULT;

        if (transport.getCategoryTransport() != CategoryTransport.CAR) {
            LOG.info("Transport's category is not Car. You need to use another function!");
            return false;
        }

        if (person.getPermissions() != allowedResidentPermission) {
            LOG.info("Human's permission is not allowed to drive a car. Actual: " + person.getPermissions() + "" +
                    ". Expected: Adult");
            return false;
        }
        LOG.info("Access for driving car was granted for: " + person.getName());
        transport.setCurrentHuman(person);
        return true;
    }

    public Boolean playSong(String song, Human human, MusicCenterAPI musicCenterAPI) {
        if (human.getPermissions() == ResidentPermission.ADULT) {
            return musicCenterAPI.getAdultSongs().contains(song);
        } else if (human.getPermissions() == ResidentPermission.CHILD) {
            return musicCenterAPI.getChildSongs().contains(song);
        }

        return false;
    }

    public Boolean fillCoffeeMachine(Human human, CoffeeMachineApi coffeeMachineApi) {
        LOG.info("ProxyAccess checks Human: " + human.getName() + " if he/she has access for Filling" +
                " the coffee machine.");
        if (human.getPermissions() == ResidentPermission.ADULT) {
            LOG.info("Permission for coffee machine granted for Human: " + human.getName());
            coffeeMachineApi.fillCoffeeMachine();
            return true;
        }
        LOG.info("Access was not granted for Human: " + human.getName() + ". Because human's permission is " +
                human.getPermissions() + ". Expected: " + ResidentPermission.ADULT);
        return false;
    }
}
