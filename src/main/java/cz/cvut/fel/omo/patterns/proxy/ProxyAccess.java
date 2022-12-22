package cz.cvut.fel.omo.patterns.proxy;

import cz.cvut.fel.omo.model.transport.CategoryTransport;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;

public class ProxyAccess {
    private static final ResidentPermission allowedResidentPermission = ResidentPermission.ADULT;

    public void accessAndDriveCar(Human person, Transport transport) {

        if (transport.getCategoryTransport() != CategoryTransport.CAR) {
            // LOG
            return;
        }

        if (person.getPermissions() != allowedResidentPermission) {
            // LOG
            return;
        }

        transport.setCurrentPerson(person);
    }
}
