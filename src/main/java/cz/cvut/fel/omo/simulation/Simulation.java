package cz.cvut.fel.omo.simulation;

import cz.cvut.fel.omo.api.model.*;
import cz.cvut.fel.omo.model.device.*;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
import cz.cvut.fel.omo.model.device.sensor.SmokeSensor;
import cz.cvut.fel.omo.model.device.sensor.TemperatureSensor;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.house.House;
import cz.cvut.fel.omo.model.user.*;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.proxy.ProxyAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.parser.ParseException;

import java.awt.event.HierarchyBoundsAdapter;
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
    private SimulationFacade simulationFacade;
    public Simulation(LocalDateTime startDateAndTime, House house) {
        this.startDateAndTime = startDateAndTime;
        this.house = house;
    }

    public Simulation() {

    }

    public void startSimulation() throws IOException, ParseException {
        simulationFacade = new SimulationFacade(house);
        loadFromConfigurationJson(1);
        airConditionerApi = new AirConditionerApi((AirConditioner) house.getOneDevice("AirConditioner"));
        coffeeMachineApi = new CoffeeMachineApi((CoffeeMachine) house.getOneDevice("CoffeeMachine"), simulationFacade);
        fridgeAPI = new FridgeAPI((Fridge) house.getOneDevice("Fridge"), simulationFacade);
        tvApi = new TVApi((TV) house.getOneDevice("TV"));
        windowApi = new WindowApi(house.getRooms());
        musicCenterAPI = new MusicCenterAPI((MusicCenter) house.getOneDevice("MusicCenter"));
        pcApi = new PCApi((PC) house.getOneDevice("PC"));
        proxyAccess = new ProxyAccess();
        transportApi = new TransportApi();
        feederForPetApi = new FeederForPetApi((FeederForPet) house.getOneDevice("FeederForPet"), simulationFacade);
        lampApi = new LampApi((Lamp) house.getOneDevice("Lamp"));
        showerApi = new ShowerApi((Shower) house.getOneDevice("Shower"));
        run();

    }

    private void loadFromConfigurationJson(int numberConfig) throws IOException, ParseException {
        String configurationName = null;

        if (numberConfig == 1) {
            configurationName = "/firstConfiguration";
        } else if (numberConfig == 2) {
            configurationName = "/secondConfiguration";
        } else {
            LOGGER.warn("Configuration with this number " + numberConfig + " doesn't exist!");
        }

        if (configurationName == null) {
            LOGGER.warn("Could not load configuration. Configuration name is null!");
            return;
        }

        loadPets(configurationName);
        loadPerson(configurationName);
        loadHouse(configurationName);
        loadDevice(configurationName);
        loadSensor(configurationName);
        loadTransport(configurationName);
        addAllSubscribers();
    }

    private void loadHouse(String nameConfig) throws IOException, ParseException {
        simulationFacade.loadHouse(nameConfig, house);
    }


    private void loadPets(String nameConfig) throws IOException, ParseException {
        simulationFacade.loadPets(nameConfig, house);
    }


    private void loadPerson(String nameConfig) throws IOException, ParseException {
        simulationFacade.loadPerson(nameConfig, house);
    }

    private void loadDevice(String nameConfig) throws IOException, ParseException {
        simulationFacade.loadDevice(nameConfig, house);
    }

    private void loadSensor(String nameConfig) throws IOException, ParseException {
        simulationFacade.loadSensor(nameConfig, house);
    }

    private void loadTransport(String nameConfig) throws IOException, ParseException {
        simulationFacade.loadTransport(nameConfig, house);
    }


    private void run() {
        int tick = 0;
        TimeSimulation timeSimulation = new TimeSimulation(LocalDateTime.parse("2022-01-01T00:00:00"));
        timeSimulation.tick();
        createFireEvent();
        while (!timeSimulation.getDateTime().isEqual(LocalDateTime.parse("2022-01-01T23:00:00"))) {
            LOGGER.info("=====================================");
            LOGGER.info("= Current time is: " + timeSimulation.getDateTime().toString() + " =");
            LOGGER.info("=====================================");
            tick += 1;

            if (tick == 3 || tick == 10 || tick == 20 || tick == 40) {
                createRandomEvents();
            }
            for (Human h : house.getHumans()) {
                createRandomUserEvents(h);
            }
            for (Pet p : house.getPets()){
                createRandomPetEvents(p);
            }
            timeSimulation.tick();
        }
    }

    // Reports. Events,
    private void createRandomPetEvents(Pet pet){
        Random random = new Random();
        int randnum = random.nextInt(4);
        switch (randnum) {
            case 0 -> {
                Human human = house.getHumanByPermission(ResidentPermission.ADULT);
                human.setActivityUser(ActivityUser.NOT_AT_HOME);
                human.setActivityUser(ActivityUser.WALK_WITH_PET);
                pet.setActivityPet(ActivityPet.WALK);
                LOGGER.info("Pet " + pet.getName() + " is on the walk with " + human.getName());
            }
            case 1 -> {
                feederForPetApi.timeForDinner();
                pet.setActivityPet(ActivityPet.EAT);
                LOGGER.info("Pet " + pet.getName() + " is going to dinner");
            }
            case 2 -> {
                pet.setActivityPet(ActivityPet.SLEEP);
                LOGGER.info("Pet " + pet.getName() + " is sleeping" );
            }
            case 3 -> {
                Human human = house.getHumanByPermission(ResidentPermission.CHILD);
                human.setActivityUser(ActivityUser.PLAY_WITH_PET);
                pet.setActivityPet(ActivityPet.PLAY);
                LOGGER.info("Human " + human.getName() + " is playing with " + pet.getName());
            }
        }

    }
    private void createRandomUserEvents(Human human) {
        Random random = new Random();
        int randNum = 0;
        List<String> food = fridgeAPI.getAllFood().keySet().stream().toList();

        switch (randNum) {
            case 0 -> {
                String oneFood = food.get(random.nextInt(food.size()));
                fridgeAPI.takeFoodFromFridge(oneFood);
                LOGGER.info("Took food from Fridge! Food: " + oneFood + ". Amount: 1");

            }
            case 1 -> {
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
                lampApi.turnOnLamp();
                LOGGER.info("Lamp is turned on");
            }
            case 14 -> {
                showerApi.turnOnShower();
                LOGGER.info(human.getName() + " turned on shower!");
            }
            case 15 -> {
                showerApi.turnOffShower();
                LOGGER.info(human.getName() + " turned on shower!");
            }
            case 16 -> {
                pcApi.turnOnPC();
                LOGGER.info(human.getName() + " playing PC!");
            }
            case 17 -> {
                pcApi.turnOffPC();
                LOGGER.info(human.getName() + " turned off PC!");
            }
            case 18 -> {
                lampApi.turnOnLamp();
//                LOGGER.info(human.getName() + " turned on Lamp. In room: " + );
            }
        }
    }

    private void createRandomPetsEvent() {

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
        smokeSensor.notifySubscribers(EventsType.Smoky,simulationFacade );
        LOGGER.info("FIRE, turn off-devices");
    }

    private void createColdTemperatureEvent() {
        LOGGER.info("So cold - close the windows");
        Random random = new Random();
        List<Sensor> temperatureSensors = house.getSensors().stream()
                .filter(TemperatureSensor.class::isInstance).toList();

        TemperatureSensor temperatureSensor = (TemperatureSensor) temperatureSensors.get(random.nextInt(temperatureSensors.size()));
        temperatureSensor.notifySubscribers(EventsType.Cold_temperature, simulationFacade);

    }

    private void createHotTemperatureEvent() {
        LOGGER.info("So hot- open windows");
        Random random = new Random();
        List<Sensor> temperatureSensors = house.getSensors().stream()
                .filter(TemperatureSensor.class::isInstance).toList();

        TemperatureSensor temperatureSensor = (TemperatureSensor) temperatureSensors.get(random.nextInt(temperatureSensors.size()));
        temperatureSensor.notifySubscribers(EventsType.Hot_temperature, simulationFacade);
    }


    private void addAllSubscribers() {
        LOGGER.info("Adding all subscriber to Sensors!");
        simulationFacade.addAllSubscribers(house);
    }
}
