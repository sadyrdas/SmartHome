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

/**
 * <p>Provides API for music center which includes access all music centers in house.</p>
 */
public class MusicCenterAPI {
    private final Set<MusicCenter> musicCenters;
    private List<String> adultSongs = new ArrayList<>(List.of("Nirvana", "Eminem", "Jackson", "IceCube", "Coulio"));
    private List<String> childSongs = new ArrayList<>(List.of("SpangeBob", "LittleDucks", "HappyMonkey", "BlueTractor"));
    private static final Logger LOG = LogManager.getLogger(Simulation.class.getName());

    private final SimulationFacade simulationFacade;

    /**
     * Main constructor
     *
     * @param musicCenters     - set of all music centers in house
     * @param simulationFacade - Facade Design pattern to hide simulation complexity behind a simple class
     */
    public MusicCenterAPI(Set<MusicCenter> musicCenters, SimulationFacade simulationFacade) {
        this.musicCenters = musicCenters;
        this.simulationFacade = simulationFacade;
    }

    /**
     * Main action
     *
     * @param musicCenter - the one music center, which is working
     * @param song        - name and type of song
     * @param human       - the one user who does an action
     * @param proxyAccess - pattern, which determines which type of user wants to use the music center.
     */
    public void playMusic(MusicCenter musicCenter, String song, Human human, ProxyAccess proxyAccess) {

        Boolean ret = proxyAccess.playSong(song, human, this);
        if (ret) {
            LOG.info("Access for " + human.getName() + " was granted.");
            human.setActivityUser(ActivityUser.LISTEN_TO_MUSIC);
            simulationFacade.addHumanEventToEventsHub(human, ActivityUser.LISTEN_TO_MUSIC);
            turnOnMusicCenterById(musicCenter.getId());
            human.countDeviceUsage(musicCenter);
        } else {
            LOG.info("Forbidden access for " + human.getName());

            simulationFacade.addDeviceEventsTypeToEventsHub(musicCenter, EventsType.Turn_off_device);
            turnOffMusicCenterById(musicCenter.getId());
        }
    }

    /**
     * Turn off- music center by id, set State to Stopped
     *
     * @param id - unique id of one specific music Center
     */

    public void turnOffMusicCenterById(Integer id) {
        MusicCenter musicCenter = getMusicCenterById(id);
        Objects.requireNonNull(musicCenter);

        musicCenter.setState(new StoppedState(musicCenter));
    }

    /**
     * Turn on- music center by id, set State to Active
     *
     * @param id - unique id of one specific music Center
     */

    public void turnOnMusicCenterById(Integer id) {
        MusicCenter musicCenter = getMusicCenterById(id);
        Objects.requireNonNull(musicCenter);

        musicCenter.setState(new ActiveState(musicCenter));
    }

    private MusicCenter getMusicCenterById(Integer id) {
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
