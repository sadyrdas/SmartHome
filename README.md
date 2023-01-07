# OMO Smart Home

## Class diagram and Use case diagram
Can be found in `/docs` folder

## Design Patterny
1. Factory: folder `src/main/java/cz/cvut/fel/omo/patterns/factory`
2. Builder: folder `src/main/java/cz/cvut/fel/omo/patterns/builder`
3. Facade: folder `src/main/java/cz/cvut/fel/omo/patterns/facade`
4. Observer: folder `src/main/java/cz/cvut/fel/omo/patterns/observer`
5. Proxy: folder `src/main/java/cz/cvut/fel/omo/patterns/proxy`
6. State: folder `src/main/java/cz/cvut/fel/omo/patterns/state`
7. Singleton: Class `src/main/java/cz/cvut/fel/omo/model/house/House.java`

## Functional Requirements

`*` z, `+` 

F1. Fully complete. Located here: `src/main/java/cz/cvut/fel/omo/model`

F2. Fully complete. Located here: `src/main/java/cz/cvut/fel/omo/api/model`

F3. Fully complete. Located in `Device` class, `setState` function

F4. Complete without functionality reduction over time. In `SimulationFacade` class, `updatePowerConsumption` method

F5. Fully complete in `src/main/java/cz/cvut/fel/omo/api/model`

F6. Complete without condition all people in one run. Events are generation in `Simulation` class, `run` function

F7. Fully complete with Observer Design pattern.

F8. Fully complete in `src/main/java/cz/cvut/fel/omo/reports`

F9. Fully complete

F10. Complete only waiting for transport usage in `Transport` class.

