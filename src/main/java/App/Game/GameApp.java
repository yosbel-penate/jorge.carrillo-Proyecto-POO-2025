package App;

import App.Components.EnemyController;
import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import App.EntityFactory.PlayersFactory;
import Domain.Entity.Types;
import Domain.Settings.SettingsGame;
import View.Maps.Maps;
import View.UI.CombatModeUI;
import View.UI.MusicService;
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
    Input input = new Input();
    UI ui = new UI();
    Board board = new Board();

    boolean combatMode;
    private Entity cyborg;
    private Entity boss;

    @Override
    protected void initSettings(GameSettings settings)
    {

        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setTitle(SettingsGame.gameTitle);

    }

    @Override
    protected void initGame()
    {
        
        Board.boardTable(NUM_TILES_Y,NUM_TILES_X, TILE_SIZE);

        //Entities Factory
        getGameWorld().addEntityFactory(new PlayersFactory());
        getGameWorld().addEntityFactory(new EnemyFactory());
        getGameWorld().addEntityFactory(new ObjectFactory());

        //Music
        MusicService.playLevel1Music();

        //Level Loader
        Maps.setLevel1();

        //Entities
        boss = FXGL.spawn("boss");
        cyborg = FXGL.spawn("Cyborg");

        //Components
        boss.addComponent(new EnemyController(cyborg, TILE_SIZE));

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

    protected void initInput()
    {
        input.movInput(cyborg,ui);
    }

    @Override
    protected void onUpdate(double tpf) {

        board.centrarPersonajes(cyborg);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
