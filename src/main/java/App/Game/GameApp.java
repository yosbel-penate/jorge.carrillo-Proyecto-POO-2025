package App.Game;

import App.Components.CombatStatsComponent;
import App.Services.CollitionService;
import App.Services.MusicService;
import Domain.Entity.Characters.Players.Cyborg;
import Domain.Entity.Characters.Players.JaxKane;
import Domain.Entity.Types;
import View.UI.GameMenu;
import View.UI.MyMenu;
import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import App.EntityFactory.PlayersFactory;
import App.Services.Input;
import Domain.Settings.SettingsGame;
import View.Maps.Maps;
import View.UI.CombatModeUI;
import View.UI.UI;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameApp extends GameApplication
{

    //Entidades
    private static Entity JaxKane;
    public static Entity currentEntity;
    private Entity cyborg;
    private Entity explore;
    private Entity droid1;
    private Entity droid2;
    private Entity droid3;
    private Entity itemAtack;
    private Entity itemLife;
    private Entity itemSpecialPoint;
    private Entity barrier;
    private Entity barrier1;
    private Entity barrier2;
    private Entity barrierDisabled;
    private Entity panel;
    private Entity panel2;

    public static ArrayList<Entity> playersSelected = new ArrayList<>();


    //Instancias
    static Input input = new Input();
    UI ui = new UI();
    Board board = new Board();
    CombatModeUI combatModeUI = new CombatModeUI(ui);
    CollitionService collitionService = new CollitionService(input);

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory(){

            @Override
            public FXGLMenu newMainMenu() {
                return new MyMenu(MenuType.MAIN_MENU);
            }

            @Override
            public FXGLMenu newGameMenu() {
                return new GameMenu();
            }
        });
        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(1536);
        settings.setHeight(1024);
        settings.setTitle(SettingsGame.gameTitle);
    }

    @Override
    protected void initGame() {

        //Tablero de juego
        Board.boardTable(NUM_TILES_Y,NUM_TILES_X, TILE_SIZE);

        //Entities Factory
        getGameWorld().addEntityFactory(new PlayersFactory());
        getGameWorld().addEntityFactory(new EnemyFactory());
        getGameWorld().addEntityFactory(new ObjectFactory());

        //Music
        MusicService.stopMainMenu();
        MusicService.playLevel1Music();

        //Level Loader
        Maps.setLevel1();

        //===================Entidades en el mapa========================
        //Explore
        explore = FXGL.spawn("explore",TILE_SIZE * 20 , TILE_SIZE * 10);
        explore = FXGL.spawn("explore",TILE_SIZE * 8 , TILE_SIZE * 13);
        //Droid1
        droid1 = FXGL.spawn("droid1",TILE_SIZE * 10, TILE_SIZE * 10);
        droid1 = FXGL.spawn("droid1",TILE_SIZE * 28, TILE_SIZE * 15);
        //Droid2
        droid2 = FXGL.spawn("droid2",TILE_SIZE * 17 , TILE_SIZE * 15);
        droid2 = FXGL.spawn("droid2",TILE_SIZE * 12, TILE_SIZE * 4);
        droid2 = FXGL.spawn("droid2",TILE_SIZE * 28, TILE_SIZE * 5);
        //Droid3
        droid3 = FXGL.spawn("droid3",TILE_SIZE * 18, TILE_SIZE * 5);
        droid3 = FXGL.spawn("droid3",TILE_SIZE * 18, TILE_SIZE * 20);
        //Barriers
        barrierDisabled = FXGL.spawn("barrierDisabled",TILE_SIZE * 16,TILE_SIZE * 13);
        barrierDisabled = FXGL.spawn("barrierDisabled",TILE_SIZE * 22,TILE_SIZE * 13);
        barrierDisabled = FXGL.spawn("barrierDisabled",TILE_SIZE * 22,TILE_SIZE * 23);
        barrierDisabled = FXGL.spawn("barrierDisabled",TILE_SIZE * 16,TILE_SIZE * 23);
        barrier = FXGL.spawn("barrier",TILE_SIZE * 16,TILE_SIZE * 3);
        barrier1 = FXGL.spawn("barrier",TILE_SIZE * 22,TILE_SIZE * 3);
        //Panels
        panel = FXGL.spawn("controlPanel",TILE_SIZE * 15,TILE_SIZE * 2);
        panel2 = FXGL.spawn("controlPanel",TILE_SIZE * 23,TILE_SIZE * 2);
        //Items
        itemSpecialPoint = FXGL.spawn("itemSpecialPoint",TILE_SIZE * 5, TILE_SIZE * 7);
        itemSpecialPoint = FXGL.spawn("itemSpecialPoint",TILE_SIZE * 25, TILE_SIZE * 5);
        itemLife = FXGL.spawn("itemLife", TILE_SIZE * 19,TILE_SIZE * 10);
        itemLife = FXGL.spawn("itemLife", TILE_SIZE * 3,TILE_SIZE * 13);
        itemAtack = FXGL.spawn("itemAtack",TILE_SIZE * 9,TILE_SIZE * 4);
        itemAtack = FXGL.spawn("itemAtack",TILE_SIZE * 19, TILE_SIZE * 15);
        //Players
        cyborg = FXGL.spawn("cyborg");
        JaxKane = FXGL.spawn("jaxKane",TILE_SIZE * 5, TILE_SIZE * 5);
        playersSelected.add(cyborg);
        playersSelected.add(JaxKane);

        currentEntity = cyborg;
        input.movInput();

    }

    public static void setActionsOnClick(String nameEntitySelected){
        for (Entity entity : playersSelected){
            String name = entity.getComponent(CombatStatsComponent.class).name;
            if (name == nameEntitySelected){
                currentEntity = entity;
                break;
            }
        }
    }

    protected void initUI() {
        ui.showUI();
        combatModeUI.showCombatUI(cyborg);
        combatModeUI.setHealthPLayer(cyborg.getComponent(CombatStatsComponent.class).getMaxHealth(),cyborg);
    }

    @Override
    protected void initPhysics()
    {
        FXGL.getPhysicsWorld().setGravity(0,0);
        collitionService.starPanelCollition(barrier1);
        collitionService.starPanelCollition(barrier);
        collitionService.startCollitionBarrier(combatModeUI);
        collitionService.startCollitionEnemy(combatModeUI);
        collitionService.startCollitionItemAtack(ui);
        collitionService.startCollitionItemSpecialPoint(combatModeUI);
        collitionService.startCollitionItemLife(combatModeUI);

    }

    @Override
    protected void initInput() {
        System.out.println("se llama el init input");

    }
    @Override
    protected void onUpdate(double tpf)
    {
        board.centrarPersonajes(cyborg);
       // System.out.println(currentEntity.getComponent(CombatStatsComponent.class).name);

    }

    public static void main(String[] args) {
        launch(args);
    }

}