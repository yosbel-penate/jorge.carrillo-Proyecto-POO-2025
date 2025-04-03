package src.main.java.Elements.Items;

// Clase base Items abstract 
class Items { 
  protected int statsElement;

public Items(int statsElement) {
    this.statsElement = statsElement;
}

public abstract void enhancementEffect();

}

// Clase MedicalKit que hereda de Items 
class MedicalKit extends Items { 
  private int increaseInLife;

public MedicalKit(int statsElement, int increaseInLife) {
    super(statsElement);
    this.increaseInLife = increaseInLife;
}

public void enhancementEffect() {
    System.out.println("El botiquín aumenta la vida en " + increaseInLife + " puntos.");
}

}


// Clase Adrenalin que hereda de Items 
class Adrenalin extends Items { 
  private int increaseInSpeed;
  
public Adrenalin(int statsElement, int increaseInSpeed) {
    super(statsElement);
    this.increaseInSpeed = increaseInSpeed;
}

public void enhancementEffect() {
    System.out.println("La adrenalina aumenta la velocidad en " + increaseInSpeed + " puntos.");
}

}

// Clase TurnController 
class TurnController { 
  private int countStepPlayers; 
  private int countStepEnemies;

public TurnController() {
    this.countStepPlayers = 0;
    this.countStepEnemies = 0;
}

public void nextTurn() {
    countStepPlayers++;
    countStepEnemies++;
    System.out.println("Nuevo turno iniciado: Turno de jugadores " + countStepPlayers + " - Turno de enemigos " + countStepEnemies);
}

   }
