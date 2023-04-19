package Characters;

import Items.Item;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class Characters {
    private String name;
    private String type;
    private int curHp;
    private int maxHp;
    private int armor;
    private int dodge;
    private int damage;
    private int endurance;
    private int strength;
    private int agility;
    private int ingenuity;
    private Image sprite;
    private Image portrait;
    private Item[] inventory;

    private GridPane myPorPane;

    public Characters(String nuName, int endurance, int strength, int agility, int ingenuity, Image mySprite, Image myPortrait) {
        this.name = nuName;
        this.curHp = endurance;
        this.maxHp = endurance;
        this.endurance = endurance;
        this.strength = strength;
        this.agility = agility;
        this.ingenuity = ingenuity;
        this.sprite = mySprite;
        this.portrait = myPortrait;
        this.armor = (int) Math.ceil((endurance + strength)/2);
        this.dodge = (int) Math.ceil((agility + ingenuity)/2);
        this.damage = strength;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurHp() {
        return curHp;
    }

    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIngenuity() {
        return ingenuity;
    }

    public void setIngenuity(int ingenuity) {
        this.ingenuity = ingenuity;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public void setPortrait(Image portrait) {
        this.portrait = portrait;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }

    public Image getSprite() {
        return sprite;
    }

    public Image getPortrait() {
        return portrait;
    }

    public void generatePorPane(){
        myPorPane = new GridPane();
        myPorPane.add(new Label(this.name), 0, 0, 4, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/hp.png", 15, 15, true, true)), 0, 1, 1, 1);
        myPorPane.add(new Label(this.maxHp + " / " + this.curHp), 1, 1, 1, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/vitality.png", 15, 15, true, true)), 2, 1, 1, 1);
        myPorPane.add(new Label("" + this.endurance), 3, 1, 1, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/hit.png", 15, 15, true, true)), 0, 2, 1, 1);
        myPorPane.add(new Label("" + this.damage), 1, 2, 1, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/str.png", 15, 15, true, true)), 2, 2, 1, 1);
        myPorPane.add(new Label("" + this.strength), 3, 2, 1, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/dodge.png", 15, 15, true, true)), 0, 3, 1, 1);
        myPorPane.add(new Label("" + this.dodge), 1, 3, 1, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/speed.png", 15, 15, true, true)), 2, 3, 1, 1);
        myPorPane.add(new Label("" + this.agility), 3, 3, 1, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/armor.png", 15, 15, true, true)), 0, 4, 1, 1);
        myPorPane.add(new Label("" + this.armor), 1, 4, 1, 1);
        myPorPane.add(new ImageView(new Image("resources/StatSym/int.png", 15, 15, true, true)), 2, 4, 1, 1);
        myPorPane.add(new Label("" + this.ingenuity), 3, 4, 1, 1);

        myPorPane.getChildren().forEach(node -> {if(node instanceof Label){((Label) node).setFont(Font.font("neo-grotesque sans-serif", FontWeight.BOLD, 10));}});
    }

    public GridPane getMyPorPane() {return myPorPane;}
}
