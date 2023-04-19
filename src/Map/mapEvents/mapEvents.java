package Map.mapEvents;

import Map.mapEvents.eventType.Fight;
import Map.mapEvents.eventType.eventType;
import Map.mapEvents.eventType.itemEnc;
import Map.mapEvents.eventType.situationChoice;

public class mapEvents {
    private String eventTypeName;
    private eventType anEvent;
    public mapEvents(){
        double randomChance = Math.random() * 100;
        if (randomChance < 100){
            this.eventTypeName = "Choice";
            this.anEvent = new situationChoice();
        }
        if (randomChance < 60){
            this.eventTypeName = "Items";
            this.anEvent = new itemEnc();
        }
        if (randomChance < 30){
            this.eventTypeName = "Fight";
            this.anEvent = new Fight(1);
        }

    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public eventType getAnEvent(){return anEvent;}
}
