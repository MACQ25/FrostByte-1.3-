package Map.mapEvents.eventType;

import Characters.Characters;
import Characters.RuinSentry;
import Items.Item;

import java.util.ArrayList;

/**
 * Here's where all the logic for all sorts of fights would go, but for now lets just code the
 * ones with the Necrons
 */
public class Fight extends eventType {
    ArrayList<Characters> enemies = new ArrayList<>();
    ArrayList<Item> loot;
    String musicTrackRef;
    String background;
    public Fight(int x){
        switch (x){
            case 1 -> {
                enemies.add(RuinSentry.warrior());
                enemies.add(RuinSentry.immortal());
                enemies.add(RuinSentry.Hexmark());
                enemies.add(RuinSentry.warrior());
                enemies.add(RuinSentry.warrior());
                this.background = "resources/sentryFight.jpg";
                this.musicTrackRef = "overlord.mp3";
            }
        }

    }

    public ArrayList<Characters> getEnemies() {
        return enemies;
    }

    public String getBackground(){return background;}

    public String getMusicTrackRef(){return musicTrackRef;}

    // This was mistaken, I am printing the array, not the fight itself
    @Override
    public String toString() {
        String muhString = "";
        for (Characters a: enemies) {
            muhString += a.getName();
        }

        return muhString;
    }

}
