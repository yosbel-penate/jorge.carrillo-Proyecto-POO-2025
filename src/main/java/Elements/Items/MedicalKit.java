package src.main.java.Elements.Items;

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

