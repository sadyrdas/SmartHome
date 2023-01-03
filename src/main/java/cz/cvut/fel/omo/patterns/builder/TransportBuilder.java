package cz.cvut.fel.omo.patterns.builder;

import cz.cvut.fel.omo.model.transport.CategoryTransport;
import cz.cvut.fel.omo.model.transport.Transport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * <p>This class implements Design Patter Builder for our example for Transport
 * https://refactoring.guru/design-patterns/builder</p>
 */
public class TransportBuilder {
    private static final Logger LOGGER = LogManager.getLogger(TransportBuilder.class.getName());
    private String name;
    private int amount;
    private CategoryTransport categoryTransport;


    public String getName() {
        return name;
    }

    public TransportBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public TransportBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public CategoryTransport getCategoryTransport(String category) {
        return switch (category) {
            case "Car" -> CategoryTransport.CAR;
            case "Bike" -> CategoryTransport.BIKE;
            case "Ski" -> CategoryTransport.SKI;
            default -> null;
        };
    }

    public TransportBuilder setCategoryTransport(String categoryTransport) {
        this.categoryTransport = getCategoryTransport(categoryTransport);
        return this;
    }

    public Transport build(){
        if (name.isEmpty() || amount == 0 || categoryTransport == null) {
            LOGGER.error("Transport attributes are empty");
            return null;
        }else {
            return new Transport(this.categoryTransport, this.name, this.amount);

        }
    }
}
