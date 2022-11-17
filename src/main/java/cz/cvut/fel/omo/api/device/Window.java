package cz.cvut.fel.omo.api.device;

public class Window {
    private boolean isOpen;

    public Window(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    //TODO OPEN BY PERSON
    //TODO ADD BLINDS
}
