package src.main.java.Elements.Items;

// Clase base Items
class Items { 
  protected int statsElement;

public Items(int statsElement) {
    this.statsElement = statsElement;
}

public abstract void enhancementEffect();

}
