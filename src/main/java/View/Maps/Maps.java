package View.Maps;
import com.almasb.fxgl.entity.level.Level;

import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;

//A partir de esta clase se van a crear los mapas de juego
public class Maps {
    public static void setLevel1()
    {
        Level level = setLevelFromMap("level_01.tmx");
    }


    public static void setLevel2 (){Level level = setLevelFromMap("level_02.tmx");}

    public static void setLevel3() 
    {
        Level level = setLevelFromMap("level_03.tmx");
    }

}