package src.main.java.Elements.Items;


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
