package App.Game;

import App.Board;
import App.Components.CombatStatsComponent;
import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import App.EntityFactory.PlayersFactory;
import App.Input;
import Domain.Settings.SettingsGame;
import View.Maps.Maps;
import View.UI.CombatModeUI;
import View.UI.UI;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameApp extends GameApplication
{
    //Instancias
    Input input = new Input();
    UI ui = new UI();
    Board board = new Board();
    private Entity cyborg;
    private Entity droid1;
    private Entity droid2;
    private Entity itemAtack;
    private Entity itemLife;
    CombatModeUI combatModeUI = new CombatModeUI(ui);
    CollitionService collitionService = new CollitionService(input);

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
        MusicService.playLevel1Music();

        //Level Loader
        Maps.setLevel1();

        //Entities
        droid1 = FXGL.spawn("droid1");
        droid2 = FXGL.spawn("droid2");
        cyborg = FXGL.spawn("cyborg");
        itemLife = FXGL.spawn("itemLife", TILE_SIZE,TILE_SIZE * 5);
        itemLife = FXGL.spawn("itemLife", TILE_SIZE * 2,TILE_SIZE * 25);

        itemAtack = FXGL.spawn("itemAtack",TILE_SIZE * 2,TILE_SIZE * 3);


        //Components
        //boss.addComponent(new EnemyController(cyborg,combatModeUI, TILE_SIZE));
        input.movInput(cyborg,combatModeUI);
    }

    protected void initUI()
    {
        ui.showUI();
        combatModeUI.showCombatUI();
        combatModeUI.setHealthEnemi(droid2.getComponent(CombatStatsComponent.class).getMaxHealth(),droid2);
        combatModeUI.setHealthEnemi(droid1.getComponent(CombatStatsComponent.class).getMaxHealth(),droid1);
        combatModeUI.setHealthPLayer(cyborg.getComponent(CombatStatsComponent.class).getMaxHealth(),cyborg);
    }

    @Override
    protected void initPhysics()
    {
        FXGL.getPhysicsWorld().setGravity(0,0);
        collitionService.startCollitionEnemy(combatModeUI);
        collitionService.startCollitionItemAtack(ui);
        collitionService.startCollitionItemLife(combatModeUI);
    }

    @Override
    protected void onUpdate(double tpf)
    {
        board.centrarPersonajes(cyborg);
    }

    public static void main(String[] args) {
        launch(args);
    }
}