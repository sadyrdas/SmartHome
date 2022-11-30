package cz.cvut.fel.omo.model.device;

import cz.cvut.fel.omo.model.events.Events_Type;
import cz.cvut.fel.omo.patterns.observer.Observer;

public class Window implements Observer {
    private boolean isOpen;

    public Window(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }
    public void close(){
        isOpen = false;
    }

    @Override
    public void update(Events_Type events_type) {
        switch (events_type){
            case Hot_temperature -> open();
            case Cold_temperature -> close();
        }

    }


    //TODO OPEN BY PERSON
    //TODO ADD BLINDS
}
