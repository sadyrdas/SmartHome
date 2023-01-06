package cz.cvut.fel.omo.model.device;

import java.util.logging.Logger;

/**
 * <p>This class describes Blind on the Window.</p>
 */
public class Blinds {

    private final Logger LOG = Logger.getLogger(this.getClass().getSimpleName());
    private final Window window;
    private Boolean isOpen = true;

    public Blinds(Window window) {
        this.window = window;
    }

    public void closeBlinds() {
        this.isOpen = false;
        LOG.info("Blinds closed");
    }

    public void openBlinds() {
        this.isOpen = true;
        LOG.info("Blinds opened");
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public Window getWindow() {
        return window;
    }
}
