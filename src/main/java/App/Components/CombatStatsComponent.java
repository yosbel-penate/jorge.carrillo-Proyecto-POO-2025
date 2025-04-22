package App.Components;

import com.almasb.fxgl.entity.component.Component;

// HealthComponent.java
public class CombatStatsComponent extends Component {
    private int maxHealth;
    public int currentHealth;
    private int atacck;

    public CombatStatsComponent(int maxHealth, int atacck) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.atacck = atacck;
    }

    public void reduceHealth(int amount) {
        currentHealth = Math.max(0, currentHealth - amount);
    }

    public void restoreHealth(int amount) {
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    // Getters
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getAtacck(){return atacck; }
}