package Items;

import Characters.Characters;

public class Magnum extends Weapon implements Ranged{
    public Magnum(String name, int value, int weight, int ammo){
        super(name, value, weight, ammo);
    }
    public void fire(Characters target){
        target.setCurHp(target.getCurHp() - 1);
    }
}
