package cz.cvut.fel.omo.api.device.energy;

public class Energy {
    private int power;

    /**
     *
     * @param power power consumption per hour. Every device has its own
     * */
    public Energy(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
