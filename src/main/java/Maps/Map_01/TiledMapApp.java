
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TMXLevelLoader;

import static com.almasb.fxgl.dsl.FXGL.*;

public class TiledMapApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Mapa Tiled con FXGL");
        settings.setVersion("1.0");
    }

    @Override
    protected void initGame() {
        // Cargar mapa Tiled
        TMXLevelLoader levelLoader = new TMXLevelLoader();
        Level level = levelLoader.load("nivel1.tmx");

        getGameWorld().setLevel(level);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
