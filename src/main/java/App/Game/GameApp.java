package App.Game;

import App.Board;
import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import App.EntityFactory.PlayersFactory;
import App.Input;
import Domain.Entity.Types;
import Domain.Settings.SettingsGame;
import View.Maps.Maps;
import View.UI.CombatModeUI;
import View.UI.UI;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameApp extends GameApplication
{

    MusicService musicService = new MusicService();
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
        settings.setWidth(TILE_SIZE * NUM_TILES_X);
        settings.setHeight(TILE_SIZE * NUM_TILES_Y);
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
        musicService.playLevel1Music();

        //Level Loader
        Maps.setLevel1();

        //Entities
        boss = FXGL.spawn("boss");
        cyborg = FXGL.spawn("Cyborg");

        input.movInput(cyborg,ui);
    }

    protected void initUI()
    {
        ui.showUI();
        combatModeUI.showCombatUI();
        ui.setHealthEnemi(6);
    }

    @Override
    protected void initPhysics()
    {
        FXGL.getPhysicsWorld().setGravity(0, 0);
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.CYBORG,Types.EntityType.ENEMY)
        {
            @Override
            protected void onCollisionBegin(Entity cyborg,Entity boss){
                combatMode = true;
                combatModeUI.combatModeSettings(combatMode,cyborg,ui);
                musicService.stopMusic();
                musicService.battleMusic();
            }
            protected void onCollitionEnd(Entity cyborg,Entity boss){
                combatMode = false;
                combatModeUI.combatModeSettings(combatMode,cyborg,ui);
            }
        });

    }

    protected void initInput() {}

    @Override
    protected void onUpdate(double tpf)
    {
        board.centrarPersonajes(cyborg);
    }

    public static void main(String[] args) {
        launch(args);
    }
}