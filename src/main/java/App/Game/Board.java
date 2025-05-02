package App.Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Domain.Settings.SettingsGame.*;

public class Board {
    static Entity tile;
    public static Entity boardTable(int NUM_TILES_Y,int NUM_TILES_X,int TILE_SIZE){
        for (int y = 0; y < NUM_TILES_Y; y++) {
            for (int x = 0; x < NUM_TILES_X; x++) {
                // Se crea una entidad para cada celda de la cuadrícula
                tile = FXGL.entityBuilder()
                        .at(x * TILE_SIZE, y * TILE_SIZE)
                        .viewWithBBox(new Rectangle(TILE_SIZE, TILE_SIZE, Color.LIGHTGRAY))
                        .buildAndAttach();
            }
        }
        return tile;
    }
    public void centrarPersonajes(Entity entity)
    {
        double screenWidth = FXGL.getAppWidth();    // 750 px
        double screenHeight = FXGL.getAppHeight();    // 500 px
        double levelWidth = TILE_SIZE * NUM_TILES_X_SCREEN;  // 40 * 50
        double levelHeight = TILE_SIZE * NUM_TILES_Y_SCREEN; // 30 * 50

        // Calcula el centro del personaje
        double playerCenterX = entity.getX() + entity.getWidth() / 2.0;
        double playerCenterY = entity.getY() + entity.getHeight() / 2.0;

        double desiredViewportX = playerCenterX - screenWidth / 2.0;
        double desiredViewportY = playerCenterY - screenHeight / 2.0;

        double viewportX = Math.max(0, Math.min(desiredViewportX, levelWidth - screenWidth));
        double viewportY = Math.max(0, Math.min(desiredViewportY, levelHeight - screenHeight));

        FXGL.getGameScene().getViewport().setX(viewportX);
        FXGL.getGameScene().getViewport().setY(viewportY);
    }
}
