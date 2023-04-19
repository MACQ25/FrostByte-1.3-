package Characters;

import javafx.scene.image.Image;

// THIS CLASS IS NOT READY AT ALL BUT I AM ALREADY CREATING IT BECAUSE I AM GETTING BORED BY THE OTHER OST
public class DepthsHorror extends Characters {
    private DepthsHorror(String nuName, int endurance, int strength, int agility, int ingenuity, Image mySprite, Image myPortrait) {
        super(nuName, endurance, strength, agility, ingenuity, mySprite, myPortrait);
    }

    public static DepthsHorror Drone(){
        return new DepthsHorror("Drone", 10, 8, 8, 10, new Image("resources/necronWarrior.gif"), new Image("resources/sWarriorPortrait.jpg"));
    }

    public static DepthsHorror Fledgling(){
        return new DepthsHorror("Fledgling", 15, 11, 6, 9, new Image("resources/necronImmortal.gif"), new Image("resources/sHexmarkPortrait.png"));
    }

    public static DepthsHorror Alpha(){
        return new DepthsHorror("Alpha", 6, 18, 10, 16, new Image("resources/necronHexmark.gif"), new Image("resources/sImmortalPortrait.jpg"));
    }

    @Override
    public String toString() {
        return super.getName();
    }
}

