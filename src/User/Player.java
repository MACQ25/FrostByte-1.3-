package User;
import Characters.*;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Player {
    private ArrayList<Characters> roster;
    private Characters[] party;

    public Player(){
        this.party = new Characters[1];
        this.party[0] = new PlayerChar("Man Person", 20, 10, 10, 10, new Image("resources/skitarii.gif"), new Image("resources/skitarii.gif"));
    }

    public Characters[] getParty() {
        return party;
    }
}