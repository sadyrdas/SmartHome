package cz.cvut.fel.omo.patterns.proxy;

import cz.cvut.fel.omo.api.model.CoffeeMachineApi;
import cz.cvut.fel.omo.api.model.MusicCenterAPI;
import cz.cvut.fel.omo.model.transport.CategoryTransport;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;

import java.util.logging.Logger;

public class ProxyAccess {
    private static final Logger LOG = Logger.getLogger(CoffeeMachineApi.class.getName());
    public Boolean accessAndDriveCar(Human person, Transport transport) {
        ResidentPermission allowedResidentPermission = ResidentPermission.ADULT;

        if (transport.getCategoryTransport() != CategoryTransport.CAR) {
            // LOG
            return false;
        }

        if (person.getPermissions() != allowedResidentPermission) {
            // LOG
            return false;
        }

        transport.setCurrentPerson(person);
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
