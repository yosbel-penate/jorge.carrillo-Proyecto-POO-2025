package mapa;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.tiled.TiledLoader;

import static com.almasb.fxgl.dsl.FXGL.*;

public class TiledMapViewerApp extends GameApplication {

 
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800); 
        settings.setHeight(600); 
        settings.setTitle("Tiled Map Viewer Level2");
        settings.setVersion("1.0");
    }
  
    protected void initGame() {
       
        Level level = getAssetLoader().loadLevel("level2.tmx", new TiledLoader());

        
        getGameWorld().setLevel(level);
    }

    public static void main(String[] args) {
        launch(args);
    }
}