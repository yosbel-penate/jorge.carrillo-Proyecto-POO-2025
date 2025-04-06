package src.main.java.Characters;

import src.main.java.Elements.Elements;
import src.main.java.Elements.Items.Items;
import src.main.java.Elements.Powers.Powers;
import src.main.java.Elements.Weapons.Weapons;
import src.main.java.Utils.Animation;
import src.main.java.Utils.AnimationChannel;

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
    private Animation animationChannels;
    private AnimationChannel animationUP;
    private AnimationChannel animationLeft;
    private AnimationChannel animationRight;
    private AnimationChannel animationDown;
    private AnimationChannel animationIdle;
    private AnimationChannel animationMov;
    private AnimationChannel animationDead;
    private AnimationChannel animationAttack;
    private int speedX;
    private int speedY;
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
    public void setAnimationsChannels() { }
    public void onUpdate() { }

}
