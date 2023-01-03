package cz.cvut.fel.omo.patterns.facade;

import cz.cvut.fel.omo.model.device.*;
import cz.cvut.fel.omo.model.device.sensor.Sensor;
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
import cz.cvut.fel.omo.simulation.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * <p>This class describes design pattern Facade</p>
 *
 */
public class SimulationFacade {

    private static final Logger LOGGER = LogManager.getLogger(SimulationFacade.class.getName());
    private static final String PATH = Objects.requireNonNull(Simulation.class.getResource("/")).getPath()
            + "configurations";

    private final House house;

    private final List<Map<Human, EventsType>> humanEventsTypeMap = new ArrayList<>();
    private final List<Map<Human, ActivityUser>> humanActivityUserMap = new ArrayList<>();
    private final List<Map<Pet, ActivityPet>> petActivityPetMap = new ArrayList<>();
    private final List<Map<Device, EventsType>> deviceEventsTypeMap = new ArrayList<>();
    private final List<Map<Sensor, EventsType>> sensorEventsTypeMap = new ArrayList<>();

    private final Map<Device, Integer> devicesPowerConsumptions = new HashMap<>();
    private final Map<Shower, Integer> showerWaterConsumptions = new HashMap<>();

    public SimulationFacade(House house) {
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public void updatePowerConsumption(Set<Device> devices) {
        if (devicesPowerConsumptions.isEmpty()) {
            for (Device device : this.house.getDevices()) {
                devicesPowerConsumptions.put(device, 0);
            }
        }

        for (Device d : devices) {
            devicesPowerConsumptions.put(d, devicesPowerConsumptions.get(d) + d.getEnergy().getPower());
        }
    }

    public void updateWaterConsumption(Set<Shower> showers) {
        if (showerWaterConsumptions.isEmpty()) {
            for (Shower shower : showers) {
                showerWaterConsumptions.put(shower, 0);
            }
        }

        for (Shower sh : showers) {
            showerWaterConsumptions.put(sh, showerWaterConsumptions.get(sh) + sh.getEnergy().getPower());
        }
    }

    public void addHumanEventToEventsHub(Human human, EventsType eventsType) {
        humanEventsTypeMap.add(Map.of(human, eventsType));
    }

    public void addHumanEventToEventsHub(Human human, ActivityUser activityUser) {
        humanActivityUserMap.add(Map.of(human, activityUser));
    }

    public void addDeviceEventsTypeToEventsHub(Device device, EventsType eventsType) {
        deviceEventsTypeMap.add(Map.of(device, eventsType));
    }

    public void addActivityPetEventToEventsHub(Pet pet, ActivityPet activityPet) {
        petActivityPetMap.add(Map.of(pet, activityPet));
    }

    public void addAllSubscribers(House house) {
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
            for (Floor f : house.getFloors()) {
                for (Room r : f.getRooms()) {
                    for (Window w : r.getWindows()) {
                        sensor.addSubscriber(w);
                    }
                }
            }
        }

        for (Device coffeeMachine : house.getSetOfSpecificDevicesByName("CoffeeMachine")) {
            for (Human h : house.getAllAdults()) {
                ((CoffeeMachine) coffeeMachine).addSubscriber(h);
            }
        }

        for (Device fridge : house.getSetOfSpecificDevicesByName("Fridge")) {
            for (Human h : house.getAllAdults()) {
                ((Fridge) fridge).addSubscriber(h);
            }
        }

        for (Device feederForPet : house.getSetOfSpecificDevicesByName("FeederForPet")) {
            for (Human h : house.getAllAdults()) {
                ((FeederForPet) feederForPet).addSubscriber(h);
            }
        }

        for (Device lamp : house.getSetOfSpecificDevicesByName("Lamp")) {
            for (Human h : house.getAllAdults()) {
                ((Lamp) lamp).addSubscriber(h);
            }
        }

        for (Device shower : house.getSetOfSpecificDevicesByName("Shower")) {
            for (Human h : house.getAllAdults()) {
                ((Shower) shower).addSubscriber(h);
            }
        }
        for (Device airConditioner : house.getSetOfSpecificDevicesByName("AirConditioner")) {
            for (Human h : house.getAllAdults()) {
                ((AirConditioner) airConditioner).addSubscriber(h);
            }
        }
        for (Device PC : house.getSetOfSpecificDevicesByName("PC")) {
            for (Human h : house.getAllAdults()) {
                ((PC) PC).addSubscriber(h);
            }
        }
        for (Device musicCenter : house.getSetOfSpecificDevicesByName("MusicCenter")) {
            for (Human h : house.getAllAdults()) {
                ((MusicCenter) musicCenter).addSubscriber(h);
            }
        }
        for (Device TV : house.getSetOfSpecificDevicesByName("TV")) {
            for (Human h : house.getAllAdults()) {
                ((TV) TV).addSubscriber(h);
            }
        }
        for (Manual manual: house.getManuals()) {
            for (Human h : house.getAllAdults()){
                manual.addSubscriber(h);
            }
        }


    }

    public JSONArray load(String nameConfig, String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(new FileReader(PATH + nameConfig + fileName));
    }

    public void loadSensor(String nameConfig, House house) throws IOException, ParseException {
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

    public void loadHouse(String nameConfig, House house) throws IOException, ParseException {
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
                    Window window = new Window(false);
                    Blinds blinds = new Blinds(window);
                    window.setBlinds(blinds);
                    windows.add(window);
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


    public void loadPets(String nameConfig, House house) throws IOException, ParseException {
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


    public void loadPerson(String nameConfig, House house) throws IOException, ParseException {
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

    public void loadDevice(String nameConfig, House house) throws IOException, ParseException {
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

    public void loadTransport(String nameConfig, House house) throws IOException, ParseException {
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
    public void loadManuals(String nameConfig, House house) throws IOException, ParseException{
        JSONArray array = load(nameConfig, "/manuals.json");
        for (Object o : array){
            JSONObject manualsJson = (JSONObject) o;
            house.addManuals(new Manual((String) manualsJson.get("content"), (int)(long) manualsJson.get("deviceId") ));

        }
        for (Manual manual: house.getManuals()){
            LOGGER.info("Created manuals " + manual.getContent());
        }
    }


    public List<Map<Human, ActivityUser>> getHumanActivityUserMap() {
        return humanActivityUserMap;
    }

    public List<Map<Pet, ActivityPet>> getPetActivityPetMap() {
        return petActivityPetMap;
    }

    public List<Map<Device, EventsType>> getDeviceEventsTypeMap() {
        return deviceEventsTypeMap;
    }

    public List<Map<Sensor, EventsType>> getSensorEventsTypeMap() {
        return sensorEventsTypeMap;
    }

    public Map<Device, Integer> getDevicesPowerConsumptions() {
        return devicesPowerConsumptions;
    }

    public Map<Shower, Integer> getShowerWaterConsumptions() {
        return showerWaterConsumptions;
    }
}
