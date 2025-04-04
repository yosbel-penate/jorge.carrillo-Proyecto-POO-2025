package src.main.java.Elements.Powers;

// Clase EnergyShield que hereda de la clase Powers
class EnergyShield extends Powers { 
  
  public EnergyShield(int range) { 
    super(range); 
  }

public void protectPlayers() {
    System.out.println("Escudo de energía protegiendo jugadores.");
}

}
