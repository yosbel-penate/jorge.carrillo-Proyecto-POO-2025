package src.main.java.Maps;
import src.main.java.Maps.EnvairomentHazards.envairomentHazard;
import src.main.java.Utils.Texture;

import java.util.ArrayList;
//A partir de esta clase se van a crear los mapas de juego
public class Maps {
    protected ArrayList<Integer> environmentalHazardsPositionX;
    protected ArrayList<Integer> environmentalHazardsPositionY;
    protected ArrayList<envairomentHazard> environmentalHazardsList;
    protected String nameMap;
    protected Texture backgroundTexture;
    protected ArrayList<Object[]> spawnsPlayers; // Usé Object[] para representar "array"
    protected ArrayList<Object[]> spawnsEnemies;
    protected ArrayList<Object[]> spawnsPower;
    protected ArrayList<Object[]> spawnsWeapons;
    protected ArrayList<Object[]> spawnsItems;
    protected Texture walls;
    protected Texture doors;

    public void loadWalls(){};
    public void loadDoors(){};
}