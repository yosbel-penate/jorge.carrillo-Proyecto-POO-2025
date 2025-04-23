package App.Components;

import com.almasb.fxgl.entity.component.Component;

// HealthComponent.java
public class CombatStatsComponent extends Component {
    private int maxHealth;
    public int currentHealth;
    private int atacck;
    private int specialPoints;
    public int currentSpecialPoints;

    //Contructor de Players
    public CombatStatsComponent(int maxHealth, int atacck, int specialPoints) {
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