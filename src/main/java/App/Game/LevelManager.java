package App.Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import java.io.InputStream;
import java.util.List;

public class LevelManager {

    private int currentLevel = 1;
    private final int maxLevel = 3;

    private PlayerState playerState = PlayerState.getInstance();

    public void loadLevel(int levelNumber) {
        if (levelNumber < 1 || levelNumber > maxLevel) {
            System.out.println("Nivel fuera de rango");
            return;
        }

        currentLevel = levelNumber;
        playerState.saveState();

        FXGL.getGameScene().getViewport().fadeOut(() -> {
            try {
                FXGL.getGameWorld().clear();
                FXGL.getGameScene().clearUINodes();

                String levelFile = String.format("assets/levels/level_%02d.tmx", levelNumber);
                InputStream mapStream = getClass().getClassLoader().getResourceAsStream(levelFile);
                if (mapStream == null) {
                    System.out.println("No se encontró el mapa: " + levelFile + ". Cargando nivel fallback...");
                    FXGL.setLevelFromMap("assets/levels/level_01.tmx");
                } else {
                    FXGL.setLevelFromMap(levelFile);
                }

                spawnPlayers();
                System.out.println("Nivel " + levelNumber + " cargado");

            } catch (Exception e) {
                System.out.println("Error al cargar nivel: " + e.getMessage());
            }

            FXGL.getGameScene().getViewport().fadeIn(null);
        });
    }

    public void nextLevel() {
        if (currentLevel < maxLevel) {
            loadLevel(currentLevel + 1);
        } else {
            System.out.println("¡Has completado todos los niveles!");
        }
    }

    private void spawnPlayers() {
        List<String> players = playerState.getSelectedPlayers();
        int baseX = 5 * 64;
        int baseY = 7 * 64;

        for (int i = 0; i < players.size(); i++) {
            String playerID = players.get(i);
            FXGL.spawn(playerID, baseX + i * 64, baseY);
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
