package Domain.Entity.Characters;

import Domain.Entity.Elements.Elements;
import Domain.Entity.Elements.Items.Items;
import Domain.Entity.Elements.Powers.Powers;
import Domain.Entity.Elements.Weapons.Weapons;
import Domain.Utils.Animation;
import Domain.Utils.AnimationChannel;

import java.util.ArrayList;

public class Characters {

    private ArrayList<Items> itemsCharacter = new ArrayList<>();
    private ArrayList<Weapons> weaponCharacter = new ArrayList<>();
    private ArrayList<Powers> powersCharacter = new ArrayList<>();
    private Elements elements;
    private int life;
    private int attack;
    private String name;
    private int stepCounter;
    private int speedX;
    public int speedY;
    private int positionX;
    private int positionY;

    public void setPositionX(int x) { this.positionX = x; }
    public void setPositionY(int y) { this.positionY = y; }
    public void moveUp() { }
    public void moveLeft() { }
    public void moveDown() { }
    public void moveRight() { }
    public void attack() { }
    public void idle() { }
    public void dead() { }

}
