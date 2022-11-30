package cz.cvut.fel.omo.api.model;

import cz.cvut.fel.omo.model.device.Window;

import java.util.logging.Logger;

public class WindowApi {
    private static final Logger LOG = Logger.getLogger(TVApi.class.getSimpleName());
    private final Window window;

    public WindowApi(Window window) {
        this.window = window;
    }

    public void openWindow() {
        window.open();
    }

    public void closeWindow() {
        window.close();
    }
}
