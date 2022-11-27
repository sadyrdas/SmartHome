package cz.cvut.fel.omo.api.transport;

public class Transport {
    private CategoryTransport categoryTransport;
    private String name;
    private int amount;

    public Transport(CategoryTransport categoryTransport, String name, int amount) {
        this.categoryTransport = categoryTransport;
        this.name = name;
        this.amount = amount;
    }

    public CategoryTransport getCategoryTransport() {
        return categoryTransport;
    }

    public void setCategoryTransport(CategoryTransport categoryTransport) {
        this.categoryTransport = categoryTransport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
