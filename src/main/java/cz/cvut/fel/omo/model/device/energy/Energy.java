package cz.cvut.fel.omo.model.device.energy;

public class Energy {
    private int power;
    private EnergyType energyType;

    /**
     *  @param power power consumption per hour. Every device has its own
     * @param energyType*/
    public Energy(int power, EnergyType energyType) {
        this.power = power;
        this.energyType = energyType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
