package App.Game;

import App.Components.CombatStatsComponent;
import App.Services.CollitionServices.CollitionService;
import App.Services.MusicService;
import View.UI.*;
import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import App.EntityFactory.PlayersFactory;
import App.Services.Input;
import Domain.Settings.SettingsGame;
import com.almasb.fxgl.app.CursorInfo;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import java.util.ArrayList;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class GameApp extends GameApplication
{

    //Entidades
    public static Entity zara;
    public static Entity JaxKane;
    private static Entity doorLevel1;
    public static Entity currentEntity;
    private Entity coin;
    private Entity tanke;
    public static Entity cyborg;
    private Entity explore;
    private Entity droid1;
    private Entity droid2;
    public static Entity marcus;
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

    LevelManager levelManager = new LevelManager();
    public static ArrayList<Entity> playersSelected = new ArrayList<>();
    static Input input = new Input();
    static UI ui = new UI();
    private Board board = new Board();
    static CombatModeUI combatModeUI = new CombatModeUI(ui);
    private final CollitionService collitionService = new CollitionService(input);

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
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

        Board.boardTable(NUM_TILES_Y, NUM_TILES_X, TILE_SIZE);
        addEntitiesFactories();
        //Music
        MusicService.stopMainMenu();
        MusicService.playLevel1Music();

        //Level Loader
        levelManager.loadLevel("level_01");
        spawnLevel_01Entities();

        input.movInput();
    }

    private void addEntitiesFactories(){
        getGameWorld().addEntityFactory(new PlayersFactory());
        getGameWorld().addEntityFactory(new EnemyFactory());
        getGameWorld().addEntityFactory(new ObjectFactory());
    }

    public void spawnLevel_01Entities (){

        doorLevel1 =  FXGL.spawn("door", TILE_SIZE * 18, 0);

        barrier = FXGL.spawn("barrier", TILE_SIZE * 16, TILE_SIZE * 3);
        barrier1 = FXGL.spawn("barrier", TILE_SIZE * 22, TILE_SIZE * 3);
    }

    private void initCollitionSeervices(){
        collitionService.starDoorCollition(doorLevel1);
        collitionService.startCollitionCoin(combatModeUI);
        collitionService.starPanelCollition(barrier);
        collitionService.startCollitionEnemy(combatModeUI);
        collitionService.startCollitionItemAtack(ui);
        collitionService.startCollitionItemLife(combatModeUI);
        collitionService.startCollitionItemSpecialPoint(combatModeUI);
        collitionService.startCollitionBarrier(combatModeUI);
    }

    public static void setActionsOnClick(String nameEntitySelected){
        try {
            for (Entity entity : playersSelected){

                String name = entity.getComponent(CombatStatsComponent.class).name;
                if (name.equals(nameEntitySelected)){

                    MusicService.playChangeCharacter();
                    currentEntity = entity;
                    ui.updateCurrentPlayerStats(currentEntity);
                    combatModeUI.setHealthPLayer(currentEntity.getComponent(CombatStatsComponent.class).getMaxHealth(),currentEntity);
                    combatModeUI.updateSpecialPointBarPLayer(currentEntity);
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("000000000000000000000000000000" + e);
        }
    }

    protected void initUI() {
        ui.showUI();
        combatModeUI.showCombatUI(currentEntity);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0,0);
        initCollitionSeervices();
    }

    @Override
    protected void onUpdate(double tpf) {

        board.centrarPersonajes(currentEntity);
        //borderEntityIdentifier();
        if (playersSelected.isEmpty()){
            System.out.println("se acabo");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}