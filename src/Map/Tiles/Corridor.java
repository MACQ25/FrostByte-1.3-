package Map.Tiles;

import javafx.scene.paint.Color;
import Map.mapEvents.mapEvents;

public class Corridor extends Tile {
    public Corridor(double x, double y, int w, int z){
        super(x, y, w ,z);
        super.fillCol = Color.WHITE;
        super.identity = "Map.Tiles.Corridor";

        super.setHasEvent(Math.random() > 0.9);

        if (super.isHasEvent()){
            super.myEvent = new mapEvents();
            if (myEvent.getEventTypeName().equals("Fight")){super.fillCol = Color.RED;}
            if (myEvent.getEventTypeName().equals("Choice")){super.fillCol = Color.CYAN;}
            if (myEvent.getEventTypeName().equals("Items")){super.fillCol = Color.GOLD;}
        }
    }


}
