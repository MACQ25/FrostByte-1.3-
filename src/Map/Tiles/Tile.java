package Map.Tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import Map.mapEvents.mapEvents;

public class Tile {
    private boolean explored;
    private boolean hasEvent;
    private double locX;
    private double locY;
    private int locXgrid;
    private int locYgrid;
    mapEvents myEvent;
    Color fillCol;
    String identity;
    public Tile(double X, double Y, int W, int Z){
        explored = false;
        locX = X;
        locY = Y;
        locXgrid = W;
        locYgrid = Z;
    }

    public double getLocX() {
        return locX;
    }

    public double getLocY() {
        return locY;
    }

    public int getLocXgrid() {return locXgrid;}

    public int getLocYgrid() {return locYgrid;}

    public void draw(GraphicsContext gc){
        gc.setFill(this.fillCol);
        gc.fillRect(this.locX, this.locY, 30, 30);
    }

    public String getIdentity() {
        return identity;
    }

    @Override
    public String toString() {
        return "[" + locX + ", " + locY + "]";
    }

    public void setHasEvent(boolean itHas){
        hasEvent = itHas;
    }

    public boolean isHasEvent() {
        return hasEvent;
    }

    public String getMyEventType() {
        return myEvent.getEventTypeName();
    }

    public mapEvents getMyEvent(){return myEvent;}

    public void setExplored(boolean t){
        this.explored = true;
    }

    public boolean isExplored() {
        return explored;
    }


}

