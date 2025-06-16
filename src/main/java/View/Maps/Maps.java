package View.Maps;

import com.almasb.fxgl.entity.level.Level;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;

// Clase para gestionar la carga de mapas del juego
public class Maps {

    public static Level setLevel1() {
        return setLevelFromMap("level_01.tmx");
    }

    public static Level setLevel2() {
        return setLevelFromMap("level_02.tmx");
    }

    public static Level setLevel3() {
        return setLevelFromMap("level_03.tmx");
    }
}