package cz.cvut.fel.omo.api.house;

import cz.cvut.fel.omo.api.device.Device;
import cz.cvut.fel.omo.api.device.Window;
import cz.cvut.fel.omo.api.user.Resident;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class RoomBase {

    private static final Logger LOGGER = LogManager.getLogger(PetBuilder.class.getName());


    private int id;
    private final Set<Resident> residents;
    private final Set<Device> devices;
    private final Set<Window> windows;


    public RoomBase(int id, Set<Resident> residents, Set<Device> devices, Set<Window> windows) {
        this.id = id;
        this.residents = residents;
        this.devices = devices;
        this.windows = windows;
    }

    public Set<Resident> getResidents() {
        return this.residents;
    }

    public Set<Device> getDevices() {
        return this.devices;
    }

    public int getId(){
        return this.id;
    }


}
