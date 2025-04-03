package src.main.java.Elements.Powers;

// Clase base Powers 
class Powers { 
  protected int range;

public Powers(int range) {
    this.range = range;
}

}

// Clase RemoteHacking 
class RemoteHacking extends Powers { 
  
  public RemoteHacking(int range) { 
    super(range); 
  }

public void breakSystemEnemies() {
    System.out.println("Hackeo remoto desactivando sistemas enemigos.");
}

}

// Clase EnergyShield 
class EnergyShield extends Powers { 
  
  public EnergyShield(int range) { 
    super(range); 
  }

public void protectPlayers() {
    System.out.println("Escudo de energía protegiendo jugadores.");
}

}



