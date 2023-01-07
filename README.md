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


## Non Functional Requirements
●	Není požadována autentizace ani autorizace - ano
●	Aplikace může běžet pouze v jedné JVM - ano
●	Aplikaci pište tak, aby byly dobře schované metody a proměnné, které nemají být dostupné ostatním třídám. Vygenerovný javadoc by měl mít co nejméně public metod a proměnných. - ano
●	Reporty jsou generovány do textového souboru - ano, '/textReports'
●	Konfigurace domu, zařízení a obyvatel domu může být nahrávána přímo z třídy nebo externího souboru (preferován je json) - ano, `src/main/resources/configuration` 1 or 2


##Požadované výstupy
●	Design ve formě use case diagramů, class diagramů a stručného popisu jak chcete úlohu realizovat - ano `/docs`
●	Veřejné API - Javadoc vygenerovaný pro funkce, kterými uživatel pracuje s vaším software - ano
●	Dvě různé konfigurace domu a pro ně vygenerovány reporty za různá období. Minimální konfigurace alespoň jednoho domu je: 6 osob, 3 zvířata, 8 typů spotřebičů, 20 ks spotřebičů, 6 místností, jedny lyže, dvě kola. -ano `src/main/resources/configuration` 
 



