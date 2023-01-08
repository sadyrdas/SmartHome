package cz.cvut.fel.omo.model.device.energy;

/**
 * <p>Class to describe Energy, which spends device.</p>
 */
public class Energy {
    private int power;
    private final EnergyType energyType;

    /**
     * @param power      power consumption per hour. Every device has its own
     * @param energyType type of Energy(Water or Electricity)
     */
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

    public EnergyType getEnergyType() {
        return energyType;
    }
}
