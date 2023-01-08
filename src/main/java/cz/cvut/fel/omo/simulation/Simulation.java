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
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import cz.cvut.fel.omo.reports.ActivityAndUsageReport;
import cz.cvut.fel.omo.reports.ConsumptionReport;
import cz.cvut.fel.omo.reports.EventReport;
import cz.cvut.fel.omo.reports.HouseConfigurationReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


public class Simulation {

    private static final Logger LOGGER = LogManager.getLogger(Simulation.class.getName());

    private final House house = House.getInstance();
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

    public Simulation() {

    }

    /**
     * Start of Simulation
     *
     * @param numberOfConfig number of package of json files
     * @throws IOException    exception
     * @throws ParseException exception
     */
    public void startSimulation(int numberOfConfig) throws IOException, ParseException {
        simulationFacade = new SimulationFacade(house);
        loadFromConfigurationJson(numberOfConfig);
        airConditionerApi = new AirConditionerApi(house.getAllAirConditioners(), simulationFacade);
        coffeeMachineApi = new CoffeeMachineApi((CoffeeMachine) house.getOneDevice("CoffeeMachine"), simulationFacade);
        fridgeAPI = new FridgeAPI((Fridge) house.getOneDevice("Fridge"), simulationFacade);
        tvApi = new TVApi(house.getAllTVs(), simulationFacade);
        windowApi = new WindowApi(house.getRooms());
        musicCenterAPI = new MusicCenterAPI(house.getAllMusicCenters(), simulationFacade);
        pcApi = new PCApi(house.getAllPCs(), simulationFacade);
        proxyAccess = new ProxyAccess();
        transportApi = new TransportApi(simulationFacade);
        feederForPetApi = new FeederForPetApi((FeederForPet) house.getOneDevice("FeederForPet"), simulationFacade);
        lampApi = new LampApi(simulationFacade, house.getAllLamps());
        showerApi = new ShowerApi((Shower) house.getOneDevice("Shower"));
        run();

        HouseConfigurationReport houseConfigurationReport =
                new HouseConfigurationReport(house, numberOfConfig);

        EventReport eventReport = new EventReport(numberOfConfig, simulationFacade);
        ConsumptionReport consumptionReport = new ConsumptionReport(numberOfConfig, simulationFacade);
        ActivityAndUsageReport activityAndUsageReport = new ActivityAndUsageReport(house, numberOfConfig);
        houseConfigurationReport.generateReport();
        eventReport.generateReport();
        consumptionReport.generateReport();
        activityAndUsageReport.generateReport();
    }


    /**
     * Main class for loads of models
     *
     * @param numberConfig number of config(json files)
     * @throws IOException    exception
     * @throws ParseException exception
     */
    public void loadFromConfigurationJson(int numberConfig) throws IOException, ParseException {
        String configurationName = null;

        if (numberConfig == 1) {
            configurationName = "/firstConfiguration";
        } else if (numberConfig == 2) {
            configurationName = "/secondConfiguration";
        } else {
            LOGGER.warn("Configuration with this number " + numberConfig + " doesn't exist! Try use 1 or 2.");
            System.exit(1);
        }

        loadPets(configurationName);
        loadPerson(configurationName);
        loadHouse(configurationName);
        loadDevice(configurationName);
        loadSensor(configurationName);
        loadTransport(configurationName);
        loadManuals(configurationName);
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

    private void loadManuals(String nameConfig) throws IOException, ParseException {
        simulationFacade.loadManuals(nameConfig, house);
    }


    private void run() {
        int tick = 0;
        TimeSimulation timeSimulation = new TimeSimulation(LocalDateTime.parse("2022-01-01T00:00:00"));
        timeSimulation.tick();

        while (!timeSimulation.getDateTime().isEqual(LocalDateTime.parse("2022-01-01T23:00:00"))) {
            timeSimulation.printCurrentTime();
            timeSimulation.updateBlindsStatusByCurrentTime(house, simulationFacade);
            tick += 1;

            if (tick == 4 || tick == 11 || tick == 21 || tick == 41) {
                createRandomEvents();
            }

            for (Human h : house.getHumans()) {
                createRandomUserEvents(h);
            }
            for (Pet p : house.getPets()) {
                createRandomPetEvents(p);
            }

            simulationFacade.updatePowerConsumption(house.getDevices());
            simulationFacade.updateWaterConsumption(house.getAllShowers());

            timeSimulation.tick();
        }
    }

    /**
     * Create random Pet Events
     *
     * @param pet the one pet that does action
     */

    private void createRandomPetEvents(Pet pet) {
        Random random = new Random();
        int randNum = random.nextInt(4);
        switch (randNum) {
            case 0 -> {
                Human human = house.getHumanByPermission(ResidentPermission.ADULT);
                human.setActivityUser(ActivityUser.NOT_AT_HOME);
                human.setActivityUser(ActivityUser.WALK_WITH_PET);
                pet.setActivityPet(ActivityPet.WALK);
                LOGGER.info("Pet " + pet.getName() + " is on the walk with " + human.getName());
            }
            case 1 -> {
                feederForPetApi.timeForDinner(pet);
                pet.setActivityPet(ActivityPet.EAT);
                LOGGER.info("Pet " + pet.getName() + " is going to dinner");
            }
            case 2 -> {
                pet.setActivityPet(ActivityPet.SLEEP);
                LOGGER.info("Pet " + pet.getName() + " is sleeping");
            }
            case 3 -> {
                Human human = house.getHumanByPermission(ResidentPermission.CHILD);
                human.setActivityUser(ActivityUser.PLAY_WITH_PET);
                pet.setActivityPet(ActivityPet.PLAY);
                LOGGER.info("Human " + human.getName() + " is playing with " + pet.getName());
            }
        }
    }

    /**
     * Create random User Events
     *
     * @param human is the one user who does action
     */
    private void createRandomUserEvents(Human human) {
        Random random = new Random();
        int randNum = random.nextInt(19);
        List<String> food = fridgeAPI.getAllFood().keySet().stream().toList();
        switch (randNum) {
            case 0 -> {
                String oneFood = food.get(random.nextInt(food.size()));
                fridgeAPI.takeFoodFromFridge(human, oneFood);
            }
            case 1 -> {
                tvApi.turnOnTvById(human, Objects.requireNonNull(tvApi.getTvs().stream()
                                .skip(new Random().nextInt(tvApi.getTvs().size()))
                                .findFirst().orElse(null))
                        .getId());
            }
            case 2 -> {
                airConditionerApi.turnOnAirConditionerById(human, Objects
                        .requireNonNull(airConditionerApi.getAirConditioners().stream()
                                .skip(new Random().nextInt(airConditionerApi.getAirConditioners().size()))
                                .findFirst().orElse(null)).getId());
            }
            case 3 -> {
                coffeeMachineApi.makeCoffee(human);
            }
            case 4 -> {
                tvApi.turnOffTvById(human, Objects.requireNonNull(tvApi.getTvs().stream()
                                .skip(new Random().nextInt(tvApi.getTvs().size()))
                                .findFirst().orElse(null))
                        .getId());
            }
            case 5 -> {
                airConditionerApi.turnOffAirConditionerById(human,
                        Objects.requireNonNull(airConditionerApi.getAirConditioners().stream()
                                        .skip(new Random().nextInt(airConditionerApi.getAirConditioners().size()))
                                        .findFirst().orElse(null))
                                .getId());
            }
            case 6 -> {
                String song = musicCenterAPI.getAdultSongs().get(random.nextInt(musicCenterAPI.getAdultSongs().size()));
                musicCenterAPI.playMusic(musicCenterAPI.getMusicCenters().stream()
                        .skip(new Random().nextInt(musicCenterAPI.getMusicCenters().size()))
                        .findFirst().orElse(null), song, human, proxyAccess);
            }
            case 7 -> {
                LOGGER.info("Random User event started. Playing music in music center.");
                String song = musicCenterAPI.getChildSongs().get(random.nextInt(musicCenterAPI.getChildSongs().size()));
                musicCenterAPI.playMusic(musicCenterAPI.getMusicCenters().stream()
                                .skip(new Random().nextInt(musicCenterAPI.getMusicCenters().size())).findFirst().orElse(null),
                        song, human, proxyAccess);
                LOGGER.info("Started playing a song: " + song + " for Children.");
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
                LOGGER.info("Random accessing Bike Transport event was started: with human name " + human.getName());
                transportApi.accessTransport(human, house.getRandomBikeTransport(), proxyAccess);
            }
            case 11 -> {
                LOGGER.info("Random accessing Ski Transport event was started: with human name " + human.getName());
                transportApi.accessTransport(human, house.getRandomSkiTransport(), proxyAccess);
            }
            case 12 -> {
                lampApi.turnOnLamp(human, Objects.requireNonNull(lampApi.getLamps().stream()
                                .skip(new Random().nextInt(lampApi.getLamps().size()))
                                .findFirst().orElse(null))
                        .getId());
                LOGGER.info(human.getName() + " turned on Lamp ");
            }
            case 13 -> {
                showerApi.turnOnShower(human);
                LOGGER.info(human.getName() + " turned on shower!");
            }
            case 14 -> {
                showerApi.turnOffShower(human);
                LOGGER.info(human.getName() + " turned on shower!");
            }
            case 15 -> {
                pcApi.turnOnPCById(human, Objects.requireNonNull(house.getAllPCs().stream()
                        .skip(new Random().nextInt(pcApi.getPcs().size()))
                        .findFirst().orElse(null)).getId());
                LOGGER.info(human.getName() + " playing PC!");
            }
            case 16 -> {
                pcApi.turnOffPCById(human, Objects.requireNonNull(house.getAllPCs().stream()
                        .skip(new Random().nextInt(pcApi.getPcs().size()))
                        .findFirst().orElse(null)).getId());
                LOGGER.info(human.getName() + " turned off PC!");
            }
            case 17 -> {
                lampApi.turnOffLamp(human, Objects.requireNonNull(lampApi.getLamps().stream()
                                .skip(new Random().nextInt(lampApi.getLamps().size()))
                                .findFirst().orElse(null))
                        .getId());
                LOGGER.info(human.getName() + " turned off Lamp ");
            }
            case 18 -> {
                Device device = house.getDevices().stream()
                        .skip(new Random().nextInt(house.getDevices().size())).findFirst().orElse(null);
                if (device != null) {
                    device.setState(new StoppedState(device));
                    LOGGER.info("Our device " + device.getName() + " is broken");
                    LOGGER.info("Human " + house.getHumanByPermission(ResidentPermission.ADULT).getName() + " read a Manual " + house.getManualByDevice(device));
                    LOGGER.info("Human repaired device: " + device.getName());
                    device.setState(new ActiveState(device));
                }

            }
        }
    }

    /**
     * Random events, which can't happen by weather
     */
    private void createRandomEvents() {
        Random random = new Random();
        int randNum = random.nextInt(3);

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
        smokeSensor.notifySubscribers(EventsType.Smoky, simulationFacade);
        LOGGER.info("FIRE, turn off-devices");
    }

    private void createColdTemperatureEvent() {
        LOGGER.info("So cold - close the windows");
        windowApi.closeWindow();
        Random random = new Random();
        List<Sensor> temperatureSensors = house.getSensors().stream()
                .filter(TemperatureSensor.class::isInstance).toList();

        TemperatureSensor temperatureSensor = (TemperatureSensor) temperatureSensors.get(random.nextInt(temperatureSensors.size()));
        temperatureSensor.notifySubscribers(EventsType.Cold_temperature, simulationFacade);

    }

    private void createHotTemperatureEvent() {
        LOGGER.info("So hot - open windows");
        windowApi.openWindow();
        List<Sensor> temperatureSensors = house.getSensors().stream()
                .filter(TemperatureSensor.class::isInstance).toList();

        TemperatureSensor temperatureSensor = (TemperatureSensor) temperatureSensors.get(new Random()
                .nextInt(temperatureSensors.size()));
        temperatureSensor.notifySubscribers(EventsType.Hot_temperature, simulationFacade);
    }


    private void addAllSubscribers() {
        LOGGER.info("Adding subscribers for subjects. Observer pattern.");
        simulationFacade.addAllSubscribers(house);
    }
}
