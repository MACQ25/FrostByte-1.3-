package Items;

import Characters.Characters;

public class Sword extends Weapon implements Melee{
    public Sword(String name, int value, int weight){
        super(name, value, weight, 0);
    }
    public void engage(Characters target){
        target.setCurHp(target.getCurHp() - 1);
    }

}
