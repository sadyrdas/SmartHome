package cz.cvut.fel.omo;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;
import cz.cvut.fel.omo.model.user.PetType;
import cz.cvut.fel.omo.model.user.ResidentPermission;
import cz.cvut.fel.omo.patterns.builder.HumanBuilder;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import cz.cvut.fel.omo.patterns.factory.DeviceFactory;
import cz.cvut.fel.omo.patterns.factory.RoomBuilder;
import cz.cvut.fel.omo.patterns.factory.SensorFactory;

import java.util.*;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger("Main");
    static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        // There should be start of our simulation
        LOG.info("Trying to create simulation from file");
        System.out.println("Hello and welcome to Smart Home Simulation. Please choose data you want to use");
        String name = scan.nextLine();

    }


    private void initSystem() {

        HumanBuilder humanBuilder = new HumanBuilder();
        PetBuilder petBuilder = new PetBuilder();
        DeviceFactory deviceFactory = new DeviceFactory();
        RoomBuilder roomBuilder = new RoomBuilder();
        SensorFactory sensorFactory = new SensorFactory();

        // Init users
        final Set<Human> users = new HashSet<>();

        users.add(humanBuilder.setName("Walter (father)")
                .setPermissions(ResidentPermission.ADULT)
                .build());
        users.add(humanBuilder.setName("Skyler (mother)")
                .setPermissions(ResidentPermission.ADULT)
                .build());
        users.add(humanBuilder.setName("Flynn (son)")
                .setPermissions(ResidentPermission.CHILD)
                .build());
        users.add(humanBuilder.setName("Holy (daughter)")
                .setPermissions(ResidentPermission.CHILD)
                .build());

        // Init pets
        final Set<Pet> pets = new HashSet<>();

        pets.add(petBuilder.setName("Duke")
                .setPermissions(ResidentPermission.PET)
                .setPetType(PetType.Dog)
                .build());

        pets.add(petBuilder.setName("Alice")
                .setPermissions(ResidentPermission.PET)
                .setPetType(PetType.Cat)
                .build());


        pets.add(petBuilder.setName("Bob")
                .setPermissions(ResidentPermission.PET)
                .setPetType(PetType.Parrot)
                .build());

        final Set<Device> devices = new HashSet<>();
        devices.add(deviceFactory.createDevice(1,"Fridge", 500));
        devices.add(deviceFactory.createDevice(2,"TV", 500));

        final Set<Sensor> sensors = new HashSet<>();
        sensors.add(sensorFactory.createSensor(1, "TemperatureSensor", 50));
        sensors.add(sensorFactory.createSensor(2, "ElectricitySensor", 100));
        sensors.add(sensorFactory.createSensor(3, "SmokeSensor", 20));

        final Set<Room> rooms = new HashSet<>();
        rooms.add(roomBuilder.addRoomName("Kitchen")
                .addDevicesToRoom(devices)
                .addUsersToRoom(users)
                .addPetsToRoom(pets)
                .build());


    }
}