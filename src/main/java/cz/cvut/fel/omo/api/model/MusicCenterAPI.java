package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.MusicCenter;
import cz.cvut.fel.omo.model.user.Human;
import cz.cvut.fel.omo.patterns.proxy.ProxyAccess;
import cz.cvut.fel.omo.patterns.state.ActiveState;
import cz.cvut.fel.omo.patterns.state.StoppedState;

import java.util.*;

public class MusicCenterAPI {
    private final MusicCenter  musicCenter;
    private List<String> adultSongs = new ArrayList<>(List.of("Nirvana","Eminem", "Jackson", "IceCube", "Coulio"));
    private List<String> childSongs = new ArrayList<>(List.of("SpangeBob", "LittleDucks", "HappyMonkey", "BlueTractor"));


    public MusicCenterAPI(MusicCenter musicCenter) {
        this.musicCenter = musicCenter;
    }

    public MusicCenter getMusicCenter() {
        return musicCenter;
    }

    public Boolean playMusic(String song, Human human,  ProxyAccess proxyAccess) {
        return proxyAccess.playSong(song, human, this);
    }

    public void turnOfMusicCenter() {
        musicCenter.setState(new StoppedState(this.musicCenter));
    }

    public void turnOnMusicCenter() {
        musicCenter.setState(new ActiveState(this.musicCenter));
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
}
