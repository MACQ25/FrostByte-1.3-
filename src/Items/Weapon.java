package Items;

public abstract class Weapon extends Item{
    public int resource;
    public Weapon(String name, int value, int weight, int resource){
        super(name, value, weight);
        this.resource = resource;
    }
}
