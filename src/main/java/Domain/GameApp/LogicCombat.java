package Domain.GameApp;

import Domain.Entity.Characters.Players.Cyborg;

public class LogicCombat {
    public int maxHealthPlayer = 10;
    public int currentHealthPlayer = 0;
    public int maximoPasos = 3;
    public int numeroActualPasos = 0;
    public int maxHealthEnemi = 2;
    public int currentHealthEnemi = 0;
    public int enemyMaxSteps = 3;
    public int enemyConsumedSteps = 0;

    public int setPLayerInitSteeps () {return 10;}

    public int setPlayerInitHealth()
    {
        return Cyborg.life;
    }

    public int playerDamage (Character character)
    {
        return Cyborg.atack;
    }

    public int cantidadPasos ()
    {
        return Cyborg.steeps;
    }
}
