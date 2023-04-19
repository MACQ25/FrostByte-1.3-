package Items;

public abstract class Item {
    private String name;
    private int value;
    private int weight;
    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }
}
