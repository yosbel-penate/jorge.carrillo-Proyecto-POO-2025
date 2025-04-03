package src.main.java.Elements.Weapons;

// Clase base Weanpons 
class Weapons { 
  protected int range;

public Weapons(int range) {
    this.range = range;
}

}

// Clase Flamethrower 
class Flamethrower extends Weapons { 
  public Flamethrower(int range) { 
    super(range); 
  }

public void fireTorrent() {
    System.out.println("Lanzallamas emitiendo torrente de fuego.");
}

}

// Clase PlasmaRifle 
class PlasmaRifle extends Weapons { 
  public PlasmaRifle(int range) { 
    super(range); 
  }

public void plasmaTorrent() {
    System.out.println("Rifle de plasma disparando torrente de plasma.");
}

}
