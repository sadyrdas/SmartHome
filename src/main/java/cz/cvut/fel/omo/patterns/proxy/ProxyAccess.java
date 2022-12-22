package cz.cvut.fel.omo.patterns.proxy;

import cz.cvut.fel.omo.api.model.MusicCenterAPI;
import cz.cvut.fel.omo.model.transport.CategoryTransport;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.ResidentPermission;

public class ProxyAccess {

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
}
