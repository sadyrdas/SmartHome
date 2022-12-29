package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.MusicCenter;
import cz.cvut.fel.omo.model.events.EventsType;
import cz.cvut.fel.omo.model.user.ActivityUser;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.facade.SimulationFacade;
import cz.cvut.fel.omo.patterns.proxy.ProxyAccess;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;
import cz.cvut.fel.omo.simulation.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class MusicCenterAPI {
    private final Set<MusicCenter> musicCenters;
    private List<String> adultSongs = new ArrayList<>(List.of("Nirvana","Eminem", "Jackson", "IceCube", "Coulio"));
    private List<String> childSongs = new ArrayList<>(List.of("SpangeBob", "LittleDucks", "HappyMonkey", "BlueTractor"));
    private static final Logger LOG = LogManager.getLogger(Simulation.class.getName());

    private final SimulationFacade simulationFacade;


    public MusicCenterAPI(Set<MusicCenter> musicCenters, SimulationFacade simulationFacade) {
        this.musicCenters = musicCenters;
        this.simulationFacade = simulationFacade;
    }

    public Boolean playMusic(MusicCenter musicCenter, String song, Human human,  ProxyAccess proxyAccess) {

        Boolean ret = proxyAccess.playSong(song, human, this);
        if (ret) {
            LOG.info("Access for " + human.getName() + " was granted.");
            human.setActivityUser(ActivityUser.LISTEN_TO_MUSIC);
            simulationFacade.addHumanEventToEventsHub(human, ActivityUser.LISTEN_TO_MUSIC);
            turnOnMusicCenterById(musicCenter.getId());
        } else {
            LOG.info("Forbidden access for " + human.getName());

            simulationFacade.addDeviceEventsTypeToEventsHub(musicCenter, EventsType.Turn_off_device);
            turnOffMusicCenterById(musicCenter.getId());
        }
        return ret;
    }

    public void turnOffMusicCenterById(Integer id) {
        MusicCenter musicCenter = getMusicCenterById(id);
        Objects.requireNonNull(musicCenter);

        musicCenter.setState(new StoppedState(musicCenter));
    }

    public void turnOnMusicCenterById(Integer id) {
        MusicCenter musicCenter = getMusicCenterById(id);
        Objects.requireNonNull(musicCenter);

        musicCenter.setState(new ActiveState(musicCenter));
    }

    private MusicCenter getMusicCenterById(Integer id){
        for (MusicCenter ms : musicCenters) {
            if (ms.getId() == id) {
                return ms;
            }
        }
        return null;
    }

    public List<String> getAdultSongs() {
        return adultSongs;
    }

    public void setAdultSongs(List<String> adultSongs) {
        this.adultSongs = adultSongs;
    }

    public List<String> getChildSongs() {
        return childSongs;
    }

    public void setChildSongs(List<String> childSongs) {
        this.childSongs = childSongs;
    }

    public Set<MusicCenter> getMusicCenters() {
        return musicCenters;
    }
}
