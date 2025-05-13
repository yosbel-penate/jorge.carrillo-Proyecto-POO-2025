package View.Maps;
import View.Maps.EnvairomentHazards.envairomentHazard;
import com.almasb.fxgl.entity.level.Level;

import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;

//A partir de esta clase se van a crear los mapas de juego
public class Maps {
    public static void setLevel1()
    {
        Level level = setLevelFromMap("level_01.tmx");
    }

}