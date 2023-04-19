package Characters;

import javafx.scene.image.Image;

public class RuinSentry extends Characters {
    private RuinSentry(String nuName, int endurance, int strength, int agility, int ingenuity, Image mySprite, Image myPortrait) {
        super(nuName, endurance, strength, agility, ingenuity, mySprite, myPortrait);
    }

    public static RuinSentry warrior(){
        return new RuinSentry("Warrior", 10, 8, 8, 10, new Image("resources/necronWarrior.gif"), new Image("resources/sWarriorPortrait.jpg"));
    }

    public static RuinSentry immortal(){
        return new RuinSentry("Immortal", 15, 10, 5, 8, new Image("resources/necronImmortal.gif"), new Image("resources/sHexmarkPortrait.png"));
    }

    public static RuinSentry Hexmark(){
        return new RuinSentry("Hexmark",  6, 18, 10, 16, new Image("resources/necronHexmark.gif"), new Image("resources/sImmortalPortrait.jpg"));
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
