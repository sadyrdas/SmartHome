package cz.cvut.fel.omo.simulation;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.house.Floor;
import cz.cvut.fel.omo.model.house.House;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Simulation {

    private static final Logger LOGGER = LogManager.getLogger(Simulation.class.getName());
    private static final String PATH = Objects.requireNonNull(Simulation.class.getResource("/")).getPath()
            + "configurations";

    private LocalDateTime startDateAndTime;
    private House house = new House();

    public Simulation(LocalDateTime startDateAndTime, House house) {
        this.startDateAndTime = startDateAndTime;
        this.house = house;
    }

    public Simulation() {

    }

    public void startSimulation() throws IOException, ParseException {
//        initSystemManually();
        loadFromConfigurationJson(1);
    }

    private void loadFromConfigurationJson(int numberConfig) throws IOException, ParseException {
        String configurationName = null;

        if (numberConfig == 1) {
            configurationName = "firstConfiguration";
        } else if (numberConfig == 2) {
            configurationName = "secondConfiguration";
        } else {
            LOGGER.warn("Configuration with this number " + numberConfig + " doesn't exist!");
        }

        if (configurationName == null) {
            LOGGER.warn("Could not load configuration. Configuration name is null!");
            return;
        }

//        load("/firstConfiguration", "/persons.json");
        loadPets("/firstConfiguration");
        loadPerson("/firstConfiguration");
        loadHouse("/firstConfiguration");
    }

    private void loadHouse(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/house.json");
        for (Object o: array) {
            JSONObject homeJson = (JSONObject) o;
            int idFloor = (int)(long)homeJson.get("floor");
            Floor floor = new Floor(idFloor);
            JSONArray roomArray = (JSONArray)homeJson.get("rooms");

            for (Object ob: roomArray) {
                JSONObject roomJson = (JSONObject) ob;
                int countWindows = (int)(long)roomJson.get("windowsCount");
                Room room = new Room((int)(long) roomJson.get("id"));
                Window window = new Window(false);
                house.addWindow(window);
                floor.addRooms(room);
            }
            house.addFloor(floor);

            LOGGER.info("Created " + roomArray.get(2) );
        }
    }


    private void loadPets(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/pets.json");
        for (Object o: array) {
            JSONObject petJson = (JSONObject) o;
            PetBuilder builder = new PetBuilder();
            house.addPet(builder.setName((String)petJson.get("name"))
                    .setPermissions((String) petJson.get("permission"))
                    .setPetType((String) petJson.get("petType"))
                    .build());

//            System.out.println((String) petJson.get("petType"));
        }
        for(Pet p: house.getPets()) {
            LOGGER.info(p.getName());
        }
    }


    private void loadPerson(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/persons.json");
        for (Object o: array) {
            JSONObject personJson = (JSONObject) o;
            HumanBuilder humanBuilder = new HumanBuilder();
            house.addHuman(humanBuilder.setName((String)personJson.get("name"))
                    .setPermissions((String) personJson.get("permission"))
                    .build());

//            System.out.println((String) petJson.get("petType"));
        }
        for(Human human: house.getHumans()) {
            LOGGER.info(human.getName());
        }
    }

    private JSONArray load(String nameConfig, String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader(PATH + nameConfig + fileName));

        for (Object o: a) {
            JSONObject person = (JSONObject) o;
        }
        return a;
    }


    private void initSystemManually() {

        HumanBuilder humanBuilder = new HumanBuilder();
        PetBuilder petBuilder = new PetBuilder();
        DeviceFactory deviceFactory = new DeviceFactory();
        RoomBuilder roomBuilder = new RoomBuilder();
        SensorFactory sensorFactory = new SensorFactory();

        // Init users
        house.addHuman(humanBuilder.setName("Walter (father)")
                .setPermissions("ADULT")
                .build());
        house.addHuman(humanBuilder.setName("Skyler (mother)")
                .setPermissions("ADULT")
                .build());
        house.addHuman(humanBuilder.setName("Flynn (son)")
                .setPermissions("ADULT")
                .build());
        house.addHuman(humanBuilder.setName("Holy (daughter)")
                .setPermissions("ADULT")
                .build());

        // Init pets
        house.addPet(petBuilder.setName("Duke")
                .setPermissions("PET")
                .setPetType("Dog")
                .build());

        house.addPet(petBuilder.setName("Alice")
                .setPermissions("PET")
                .setPetType("Cat")
                .build());

        house.addPet(petBuilder.setName("Bob")
                .setPermissions("PET")
                .setPetType("Parrot")
                .build());

        house.addDevice(deviceFactory.createDevice(1,"Fridge", 500));
        house.addDevice(deviceFactory.createDevice(2,"TV", 500));

        house.addSensor(sensorFactory.createSensor(1, "TemperatureSensor", 50));
        house.addSensor(sensorFactory.createSensor(2, "ElectricitySensor", 100));
        house.addSensor(sensorFactory.createSensor(3, "SmokeSensor", 20));

//        house.addRoom(roomBuilder.addRoomName("Kitchen")
//                .addDevicesToRoom(devices)
//                .addUsersToRoom(users)
//                .addPetsToRoom(pets)
//                .build());
//


    }
}
