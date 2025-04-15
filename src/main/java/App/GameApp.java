package App;
import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import App.EntityFactory.PlayersFactory;
import Domain.Entity.Types;
import Domain.Settings.SettingsGame;
import View.UI.CombatModeUI;
import View.UI.UI;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.physics.CollisionHandler;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGL.setLevelFromMap;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameApp extends GameApplication {
    CombatModeUI combatModeUI = new CombatModeUI();
    boolean combatMode;

    private Entity cyborg;
    private Entity boss;
    //private int playerHealth = 5;

    Input input = new Input();
    UI ui = new UI();
    Board board = new Board();

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setTitle(SettingsGame.gameTitle);
    }

    @Override
    protected void initGame() {
        Board.boardTable(NUM_TILES_Y,NUM_TILES_X, TILE_SIZE);
        getGameWorld().addEntityFactory(new PlayersFactory());
        getGameWorld().addEntityFactory(new EnemyFactory());
        getGameWorld().addEntityFactory(new ObjectFactory());
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("loading.wav"));

        Level level = setLevelFromMap("level_01.tmx");

        boss = FXGL.spawn("boss");
        cyborg = FXGL.spawn("Cyborg");
        input.movInput(cyborg);
    }

    protected void initUI()
    {
        ui.showUI();
        ui.setHealthEnemi(6);
        combatModeUI.showCombatUI();
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, 0);
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.CYBORG, Types.EntityType.ENEMY)
        {
            @Override
            protected void onCollisionBegin(Entity cyborg, Entity boss) {
                combatMode = true;
                combatModeUI.combatModeSettings(combatMode,cyborg,ui);
                FXGL.getAudioPlayer().stopAllMusic();
                FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("battle.wav"));
            }
            protected void onCollisionEnd(Entity cyborg, Entity boss) {
                combatMode = false;
                combatModeUI.combatModeSettings(combatMode,cyborg,ui);
            }
        });
    }

    protected void initInput(){}



    @Override
    protected void onUpdate(double tpf) {

        board.centrarPersonajes(cyborg);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
