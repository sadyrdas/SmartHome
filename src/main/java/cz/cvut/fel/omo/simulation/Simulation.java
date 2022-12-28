package cz.cvut.fel.omo.simulation;

import cz.cvut.fel.omo.api.model.*;
import cz.cvut.fel.omo.model.device.*;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.device.sensor.SmokeSensor;
import cz.cvut.fel.omo.model.device.sensor.TemperatureSensor;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.house.Floor;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.room.Room;
import cz.cvut.fel.omo.model.transport.Transport;
import cz.cvut.fel.omo.model.user.*;
import cz.cvut.fel.omo.patterns.builder.HumanBuilder;
import cz.cvut.fel.omo.patterns.builder.PetBuilder;
import cz.cvut.fel.omo.patterns.builder.TransportBuilder;
import cz.cvut.fel.omo.patterns.factory.DeviceFactory;
import cz.cvut.fel.omo.patterns.factory.RoomBuilder;
import cz.cvut.fel.omo.patterns.factory.SensorFactory;
import cz.cvut.fel.omo.patterns.proxy.ProxyAccess;
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
    private AirConditionerApi airConditionerApi;
    private CoffeeMachineApi coffeeMachineApi;
    private FridgeAPI fridgeAPI;
    private TVApi tvApi;
    private WindowApi windowApi;
    private MusicCenterAPI musicCenterAPI;
    private PCApi pcApi;
    private ProxyAccess proxyAccess;
    private TransportApi transportApi;
    private FeederForPetApi feederForPetApi;
    private LampApi lampApi;
    private ShowerApi showerApi;
    public Simulation(LocalDateTime startDateAndTime, House house) {
        this.startDateAndTime = startDateAndTime;
        this.house = house;
    }

    public Simulation() {

    }

    public void startSimulation() throws IOException, ParseException {
        loadFromConfigurationJson(1);
        airConditionerApi = new AirConditionerApi((AirConditioner) house.getOneDevice("AirConditioner"));
        coffeeMachineApi = new CoffeeMachineApi((CoffeeMachine) house.getOneDevice("CoffeeMachine"));
        fridgeAPI = new FridgeAPI((Fridge) house.getOneDevice("Fridge"));
        tvApi = new TVApi((TV) house.getOneDevice("TV"));
        windowApi = new WindowApi(house.getRooms());
        musicCenterAPI = new MusicCenterAPI((MusicCenter) house.getOneDevice("MusicCenter"));
        pcApi = new PCApi((PC) house.getOneDevice("PC"));
        proxyAccess = new ProxyAccess();
        transportApi = new TransportApi();
        feederForPetApi = new FeederForPetApi((FeederForPet) house.getOneDevice("FeederForPet"));
        lampApi = new LampApi((Lamp) house.getOneDevice("Lamp"));
        showerApi = new ShowerApi((Shower) house.getOneDevice("Shower"));

        run();

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
        addAllSubscribers();
    }

    private void loadHouse(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/house.json");
        RoomBuilder roomBuilder = new RoomBuilder();

        for (Object o : array) {
            JSONObject houseJson = (JSONObject) o;
            int idFloor = (int) (long) houseJson.get("floor");
            Floor floor = new Floor(idFloor);
            JSONArray roomArray = (JSONArray) houseJson.get("rooms");

            for (Object ob : roomArray) {
                JSONObject roomJson = (JSONObject) ob;
                int countWindows = (int) (long) roomJson.get("windowsCount");

                Set<Window> windows = new HashSet<>();
                for (int i = 0; i < countWindows; i++) {
                    windows.add(new Window(false));
                }

                Room room = roomBuilder.setId((int) (long) roomJson.get("id"))
                        .addRoomName((String) roomJson.get("name"))
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
        for (Object o : array) {
            JSONObject petJson = (JSONObject) o;
            PetBuilder builder = new PetBuilder();
            house.addPet(builder.setName((String) petJson.get("name"))
                    .setPermissions((String) petJson.get("permission"))
                    .setPetType((String) petJson.get("petType"))
                    .build());
        }
        for (Pet p : house.getPets()) {
            LOGGER.info("Created pets " + p.getName());
        }
    }


    private void loadPerson(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/persons.json");
        for (Object o : array) {
            JSONObject personJson = (JSONObject) o;
            HumanBuilder humanBuilder = new HumanBuilder();
            house.addHuman(humanBuilder.setName((String) personJson.get("name"))
                    .setPermissions((String) personJson.get("permission"))
                    .build());
        }
        for (Human human : house.getHumans()) {
            LOGGER.info("Created humans " + human.getName());
        }
    }

    private void loadDevice(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/devices.json");
        for (Object o : array) {
            JSONObject deviceJson = (JSONObject) o;
            DeviceFactory deviceFactory = new DeviceFactory();
            Room room = house.getRoomById((int) (long) deviceJson.get("idRoom"));
            house.addDevice(deviceFactory.createDevice((int) (long) deviceJson.get("id"), (String) deviceJson.get("name"), (int) (long) deviceJson.get("baseEnergyConsumption"), room));
        }
        for (Device device : house.getDevices()) {
            LOGGER.info("Created devices " + device.getName());
        }
    }

    private void loadSensor(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/sensor.json");
        for (Object o : array) {
            JSONObject sensorJson = (JSONObject) o;
            SensorFactory sensorFactory = new SensorFactory();
            Room room = house.getRoomById((int) (long) sensorJson.get("idRoom"));
            house.addSensor(sensorFactory.createSensor((int) (long) sensorJson.get("id"), (String) sensorJson.get("name"), (int) (long) sensorJson.get("baseEnergyConsumption"), room));

        }
        for (Sensor sensor : house.getSensors()) {
            LOGGER.info("Created sensors " + sensor.getName());
        }
    }

    private void loadTransport(String nameConfig) throws IOException, ParseException {
        JSONArray array = load(nameConfig, "/transport.json");
        for (Object o : array) {
            JSONObject transportJson = (JSONObject) o;
            TransportBuilder builder = new TransportBuilder();
            house.addTransport(builder.setName((String) transportJson.get("name"))
                    .setAmount((int) (long) transportJson.get("amount"))
                    .setCategoryTransport((String) transportJson.get("categoryTransport"))
                    .build());
        }
        for (Transport t : house.getTransports()) {
            LOGGER.info("Created transports " + t.getName());
        }
    }

    private JSONArray load(String nameConfig, String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(new FileReader(PATH + nameConfig + fileName));
    }

    private void run() {
        int tick = 0;
        TimeSimulation timeSimulation = new TimeSimulation(LocalDateTime.parse("2022-01-01T00:00:00"));
        timeSimulation.tick();
        createFireEvent();
        while (!timeSimulation.getDateTime().isEqual(LocalDateTime.parse("2022-01-01T23:00:00"))) {
            LOGGER.info("Current time is: " + timeSimulation.getDateTime().toString());
            tick += 1;
            // Every device should know current time
            timeSimulation.setDateTime(timeSimulation.getDateTime().plusHours(1));
            if (tick == 3 || tick == 10 || tick == 20 || tick == 40) {
                createRandomEvents();
                for (Human h : house.getHumans()) {
                    createRandomUserEvents(h);
                }
            }
        }

        // Reports. Events,
    }

    private void createRandomUserEvents(Human human) {
        Random random = new Random();
        int randNum = 12;
        List<String> food = fridgeAPI.getAllFood().keySet().stream().toList();

        switch (randNum){
            case 0 ->{
                String oneFood = food.get(random.nextInt(food.size()));
                fridgeAPI.takeFoodFromFridge(oneFood);
                LOGGER.info("Took food from Fridge! Food: " + oneFood + ". Amount: 1");

            }
            case 1 ->{
                tvApi.turnOnTv();
                LOGGER.info("TV is turned on!");
            }
            case 2 -> {
                airConditionerApi.turnOnAirConditioner();
                LOGGER.info("AirConditioner is turned on");
            }
            case 3 -> {
                coffeeMachineApi.makeCoffee();
                LOGGER.info("Your coffee is ready! " + "MlOfMilk " + coffeeMachineApi.getMlOfMilk() + "MlOfWater " + coffeeMachineApi.getMlOfWater() + "amountBeans " + coffeeMachineApi.getAmountOfBeans());
            }
            case 4 -> {
                tvApi.turnOfTv();
                LOGGER.info("TV is turned off!");
            }
            case 5 -> {
                airConditionerApi.turnOffAirConditioner();
                LOGGER.info("AirConditioner is turned off");
            }
            case 6 -> {
                String song = musicCenterAPI.getAdultSongs().get(random.nextInt(musicCenterAPI.getAdultSongs().size()));
                musicCenterAPI.playMusic(song, human, proxyAccess);
                LOGGER.info("Started playing a song: " + song + " for Adults.");
            }
            case 7 -> {
                LOGGER.info("Random User event started. Playing music in music center.");
                String song = musicCenterAPI.getChildSongs().get(random.nextInt(musicCenterAPI.getChildSongs().size()));
                musicCenterAPI.playMusic(song, human, proxyAccess);
                LOGGER.info("Started playing a song: " + song + " for Childs.");
            }

            case 8 -> {
                LOGGER.info("Random accessing Car Transport event was started: with human name " + human.getName());
                transportApi.accessTransport(human, house.getRandomCarTransport(), proxyAccess);
            }
            case 9 -> {
                LOGGER.info("Random User Event. Ending using a Car Transport.");
                transportApi.removeHuman(house.getRandomCarTransport(), human);
            }
            case 10 -> {
                LOGGER.info("Random accessing Ski Transport event was started: with human name " + human.getName());
                transportApi.accessTransport(human, house.getRandomCarTransport(), proxyAccess);
            }
            case 11 -> {
                LOGGER.info("Random accessing Ski Transport event was started: with human name " + human.getName());
                transportApi.accessTransport(human, house.getRandomSkiTransport(), proxyAccess);
            }
            case 12 -> {
                feederForPetApi.timeForDinner();
                LOGGER.info("Dinner is ready");
            }
            case 13 -> {
                lampApi.turnOnAirConditioner();
                LOGGER.info("Lamp is turned on");
            }
        }


    }

    private void createRandomEvents() {
        Random random = new Random();
        int randNum = random.nextInt(5);

        switch (randNum) {
            case 0 -> createFireEvent();
            case 1 -> createColdTemperatureEvent();
            case 2 -> createHotTemperatureEvent();
        }
    }


    private void createFireEvent() {
        LOGGER.info("WE ARE ON FIRE-RUN!");
        Random random = new Random();
        List<Sensor> smokeSensors = house.getSensors().stream()
                .filter(SmokeSensor.class::isInstance).toList();


        SmokeSensor smokeSensor = (SmokeSensor) smokeSensors.get(random.nextInt(smokeSensors.size()));
        smokeSensor.notifySubscribers(EventsType.Smoky);
        LOGGER.info("FIRE, turn off-devices");
    }

    private void createColdTemperatureEvent() {
        LOGGER.info("So cold - close the windows");
        Random random = new Random();
        List<Sensor> temperatureSensors = house.getSensors().stream()
                .filter(TemperatureSensor.class::isInstance).toList();

        TemperatureSensor temperatureSensor = (TemperatureSensor) temperatureSensors.get(random.nextInt(temperatureSensors.size()));
        temperatureSensor.notifySubscribers(EventsType.Cold_temperature);

    }

    private void createHotTemperatureEvent() {
        LOGGER.info("So hot- open windows");
        Random random = new Random();
        List<Sensor> temperatureSensors = house.getSensors().stream()
                .filter(TemperatureSensor.class::isInstance).toList();

        TemperatureSensor temperatureSensor = (TemperatureSensor) temperatureSensors.get(random.nextInt(temperatureSensors.size()));
        temperatureSensor.notifySubscribers(EventsType.Hot_temperature);
    }



    private void addAllSubscribers() {
        LOGGER.info("Adding all subscriber to Sensors!");
        for (Sensor sensor : house.getSensors()) {
            for (Device device : house.getDevices()) {
                sensor.addSubscriber(device);
            }
            for (Human human : house.getHumans()) {
                sensor.addSubscriber(human);
            }
            for (Pet pet : house.getPets()) {
                sensor.addSubscriber(pet);
            }
            for (Floor f : house.getFloors()){
                for (Room r : f.getRooms() ){
                    for (Window w : r.getWindows()){
                        sensor.addSubscriber(w);
                }
            }
            }
        }
    }
}
