package src.main.java.Elements.Powers;

// Clase base Powers
class Powers { 
  protected int range;

public Powers(int range) {
    this.range = range;
}

public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void showInfo() {
        System.out.println("Alcance del poder: " + range + " metros.");
    }
  
}

