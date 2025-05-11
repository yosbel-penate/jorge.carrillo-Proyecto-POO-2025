package Domain.Entity.Characters;

public class Characters {

    //Atributos Genericos a los personajes (en general)
    public String name;
    public int life;
    public int atack;
    public int hitBox;

    public void lostLife(int damage){
        this.life =- damage;
    }


}
