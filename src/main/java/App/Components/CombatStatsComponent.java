package App.Components;

import com.almasb.fxgl.entity.component.Component;

public class CombatStatsComponent extends Component {
    public String name;
    private int maxHealth;
    public int currentHealth;
    public int atacck;
    private int specialPoints;
    public int currentSpecialPoints;

    //Contructor de Players
    public CombatStatsComponent(int maxHealth, int atacck, int specialPoints,String name) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.atacck = atacck;
        this.specialPoints = specialPoints;
        this.currentSpecialPoints = specialPoints;
    }

    //Constructor de enemigos
    public CombatStatsComponent(int maxHealth, int atacck) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.atacck = atacck;
    }

    // Getters
    public int getSpecialPoints(){return specialPoints; }
    public int getCurrentSpecialPoints(){return currentSpecialPoints; }
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getAtacck(){return atacck; }

}