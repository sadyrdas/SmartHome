package cz.cvut.fel.omo.simulation;

import cz.cvut.fel.omo.model.device.Device;
import cz.cvut.fel.omo.model.device.Window;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.events.Event;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.house.Floor;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.ActivityPet;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.model.user.Pet;
import cz.cvut.fel.omo.patterns.builder.HumanBuilder;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import cz.cvut.fel.omo.patterns.builder.TransportBuilder;
import cz.cvut.fel.omo.patterns.factory.DeviceFactory;
import cz.cvut.fel.omo.patterns.factory.RoomBuilder;
import cz.cvut.fel.omo.patterns.factory.SensorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Simulation {

    private static final Logger LOGGER = LogManager.getLogger(Simulation.class.getName());
    private static final String PATH = Objects.requireNonNull(Simulation.class.getResource("/")).getPath()
            + "configurations";

    private LocalDateTime startDateAndTime;
    private House house = House.getInstance();

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

        load("/firstConfiguration", "/persons.json");
        loadPets("/firstConfiguration");
        loadPerson("/firstConfiguration");
        loadHouse("/firstConfiguration");
        loadDevice("/firstConfiguration");
        loadSensor("/firstConfiguration");
        loadTransport("/firstConfiguration");
        createRandomEvents();
    }

    private void loadHouse(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/house.json");
        RoomBuilder roomBuilder = new RoomBuilder();

        for (Object o: array) {
            JSONObject houseJson = (JSONObject) o;
            int idFloor = (int)(long)houseJson.get("floor");
            Floor floor = new Floor(idFloor);
            JSONArray roomArray = (JSONArray)houseJson.get("rooms");

            for (Object ob: roomArray) {
                JSONObject roomJson = (JSONObject) ob;
                int countWindows = (int)(long)roomJson.get("windowsCount");

                Set<Window> windows = new HashSet<>();
                for (int i = 0; i < countWindows; i++) {
                    windows.add(new Window(false));
                }

                Room room = roomBuilder.setId((int)(long) roomJson.get("id"))
                        .addRoomName((String)roomJson.get("name"))
                        .setWindowsCount(countWindows)
                        .addWindowsToRoom(windows)
                        .build();
                floor.addRoom(room);
            }
            house.addFloor(floor);

            for (Room r : floor.getRooms()) {
                LOGGER.info("Created rooms " + r.getRoomName());
            }
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
        }
        for(Pet p: house.getPets()) {
            LOGGER.info("Created pets " + p.getName());
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
        }
        for(Human human: house.getHumans()) {
            LOGGER.info("Created humans " + human.getName());
        }
    }

    private void loadDevice(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/devices.json");
        for (Object o: array) {
            JSONObject deviceJson = (JSONObject) o;
            DeviceFactory deviceFactory = new DeviceFactory();
            Room room = house.getRoomById((int)(long)deviceJson.get("idRoom"));
            house.addDevice(deviceFactory.createDevice((int)(long)deviceJson.get("id"), (String)deviceJson.get("name"), (int)(long)deviceJson.get("baseEnergyConsumption"), room ));
        }
        for(Device device : house.getDevices()) {
            LOGGER.info("Created devices " + device.getName());
        }
    }

    private void loadSensor(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/sensor.json");
        for (Object o: array) {
            JSONObject sensorJson = (JSONObject) o;
            SensorFactory sensorFactory = new SensorFactory();
            Room room = house.getRoomById((int)(long)sensorJson.get("idRoom"));
            house.addSensor(sensorFactory.createSensor((int)(long) sensorJson.get("id"), (String) sensorJson.get("name"),(int)(long)sensorJson.get("baseEnergyConsumption"), room));

        }
        for(Sensor sensor : house.getSensors()) {
            LOGGER.info("Created sensors " + sensor.getName());
        }
    }

    private void loadTransport(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/transport.json");
        for (Object o: array) {
            JSONObject transportJson = (JSONObject) o;
            TransportBuilder builder = new TransportBuilder();
            house.addTransport(builder.setName((String)transportJson.get("name"))
                    .setAmount((int)(long) transportJson.get("amount"))
                    .setCategoryTransport((String) transportJson.get("categoryTransport"))
                    .build());
        }
        for(Transport t: house.getTransports()) {
            LOGGER.info("Created transports " + t.getName());
        }
    }

    private JSONArray load(String nameConfig, String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(new FileReader(PATH + nameConfig + fileName));
    }

    private void createRandomEvents(){
        TimeSimulation timeSimulation = new TimeSimulation(LocalDateTime.parse("2022-01-01T00:00:00"));
        List<Event> randomEvents = new ArrayList<>();
//        randomEvents.add(new Event(EventsType.OpenWindow,timeSimulation.getHours(), timeSimulation.getHours()));
//        randomEvents.add(new Event(EventsType.CloseWindow,timeSimulation.getHours(), timeSimulation.getHours()));
//        randomEvents.add(new Event(EventsType.Cold_temperature,timeSimulation.getHours(), timeSimulation.getHours()));
//        randomEvents.add(new Event(EventsType.Hot_temperature,timeSimulation.getHours(), timeSimulation.getHours()));
//        randomEvents.add(new Event(EventsType.Off_device,timeSimulation.getHours(), timeSimulation.getHours()));
//        randomEvents.add(new Event(EventsType.On_device,timeSimulation.getHours(), timeSimulation.getHours()));
//        randomEvents.add(new Event(EventsType.Smoky,timeSimulation.getHours(), timeSimulation.getHours()));

        while (!timeSimulation.getDateTime().isEqual(LocalDateTime.parse("2022-01-19T23:55:00"))){
            LOGGER.warn(timeSimulation.getDateTime().toString());
            timeSimulation.setDateTime(timeSimulation.getDateTime().plusMinutes(5));
//            if (timeSimulation.getHours() == 0){
//                for (Human human : house.getHumans()){
//                    human.setActivityUser(ActivityUser.SLEEP);
//                }
//                for (Pet pet : house.getPets()){
//                    pet.setActivityPet(ActivityPet.SLEEP);
//                }
//            }
//            if ()

        }
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

//        house.addDevice(deviceFactory.createDevice(1,"Fridge", 500,));
//        house.addDevice(deviceFactory.createDevice(2,"TV", 500));

//        house.addSensor(sensorFactory.createSensor(1, "TemperatureSensor", 50));
//        house.addSensor(sensorFactory.createSensor(2, "ElectricitySensor", 100));
//        house.addSensor(sensorFactory.createSensor(3, "SmokeSensor", 20));

//        house.addRoom(roomBuilder.addRoomName("Kitchen")
//                .addDevicesToRoom(devices)
//                .addUsersToRoom(users)
//                .addPetsToRoom(pets)
//                .build());
//


    }
}
