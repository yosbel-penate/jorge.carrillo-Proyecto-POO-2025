package src.main.java.Elements.Weapons;

// Clase base Weanpons
class Weapons { 
  protected int range;

public Weapons(int range) {
    this.range = range;
}

public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void showInfo() {
        System.out.println("Alcance del arma: " + range + " metros.");  

}

}
