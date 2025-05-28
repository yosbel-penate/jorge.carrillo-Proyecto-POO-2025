package App.Game;

import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import View.UI.EscenaSeleccion;
import View.UI.UI;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.List;

import static App.Game.GameApp.*;
import static Domain.Settings.SettingsGame.TILE_SIZE;
import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;

public class LevelManager
{


    public void loadLevel(String levelName) {
        Level level = setLevelFromMap(levelName + ".tmx");

        spawnEntites(levelName); //carga los enemigos dependiendo del nivel
        spawnPlayer(levelName);
    }


    private void spawnEntites(String levelName) {
        List<Entity> enemies = EnemyFactory.getEnemiesForLevel(levelName);
        List<Entity> objects = ObjectFactory.getObjectsForLevel(levelName);
    }

    int contador = -1;
    private Point2D setPosition(String level){
        if (contador == 4) contador = 0;
        if (level.equals("level_01")){
            contador ++;
            return posiciones.get(contador);
        }else {
            contador ++;
            return posicionesParaNiveles.get(contador);
        }

    }
    private final List<Point2D> posiciones = List.of(
            new Point2D(TILE_SIZE * 4, TILE_SIZE * 6),
            new Point2D(TILE_SIZE * 6, TILE_SIZE * 6),
            new Point2D(TILE_SIZE * 4, TILE_SIZE * 4),
            new Point2D(TILE_SIZE * 6, TILE_SIZE * 4),
            new Point2D(TILE_SIZE * 7, TILE_SIZE * 5)
    );

    private final List<Point2D> posicionesParaNiveles = List.of(
            new Point2D(TILE_SIZE * 18, TILE_SIZE * 27),
            new Point2D(TILE_SIZE * 20, TILE_SIZE * 27),
            new Point2D(TILE_SIZE * 18, TILE_SIZE * 24),
            new Point2D(TILE_SIZE * 20, TILE_SIZE * 24),
            new Point2D(TILE_SIZE * 19, TILE_SIZE * 18)
    );

    public void spawnPlayer(String level) {
        if (EscenaSeleccion.jaxKaneBool) {
            JaxKane = FXGL.spawn("jaxKane",setPosition(level));
            playersSelected.add(JaxKane);
            currentEntity = JaxKane;
        }

        if (EscenaSeleccion.cyborgBool){
            cyborg = FXGL.spawn("cyborg",setPosition(level));
            playersSelected.add(cyborg);
            currentEntity = cyborg;
        }

        if (EscenaSeleccion.zaraQuinnBool) {
            zara = FXGL.spawn("zara",setPosition(level));
            playersSelected.add(zara);
            currentEntity =zara;
        }

        if (EscenaSeleccion.marcusBool) {
            marcus = FXGL.spawn("marcus",setPosition(level));
            playersSelected.add(marcus);
            currentEntity = marcus;
        }

        if (EscenaSeleccion.toxicBool){
            toxic = FXGL.spawn("toxic",setPosition(level));
            playersSelected.add(toxic);
            currentEntity = toxic;
        }
    }
}
