package src.main.java.Elements.Items;

// Clase base Items abstract 
class Items { 
  protected int statsElement;

public Items(int statsElement) {
    this.statsElement = statsElement;
}

public abstract void enhancementEffect();

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
