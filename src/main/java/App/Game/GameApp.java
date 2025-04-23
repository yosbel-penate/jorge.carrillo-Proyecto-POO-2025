package App.Game;

import App.Board;
import App.Components.CombatStatsComponent;
import App.Components.MyMenu;
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
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private Entity droid3;
    private Entity itemAtack;
    private Entity itemLife;
    CombatModeUI combatModeUI = new CombatModeUI(ui);
    CollitionService collitionService = new CollitionService(input);


    private Stage loadingStage;

    @Override
    protected void initSettings(GameSettings settings)
    {
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory(){
            @Override
            public FXGLMenu newMainMenu() {
                return new MyMenu();              // <– nuestro menú con fondo nuevo
            }
        });
        settings.setDeveloperMenuEnabled(true);
        settings.setWidth(1920);
        settings.setHeight(1080);
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
        MusicService.stopMainMenu();
        MusicService.playLevel1Music();

        //Level Loader
        Maps.setLevel1();

        //Entities
        //golem = FXGL.spawn("golem",TILE_SIZE * 18, TILE_SIZE * 8);
        droid1 = FXGL.spawn("droid1",TILE_SIZE * 10, TILE_SIZE * 10);
        droid1 = FXGL.spawn("droid1",TILE_SIZE * 28, TILE_SIZE * 15);


        droid2 = FXGL.spawn("droid2",TILE_SIZE * 12, TILE_SIZE * 4);
        droid2 = FXGL.spawn("droid2",TILE_SIZE * 28, TILE_SIZE * 5);

        droid3 = FXGL.spawn("droid3",TILE_SIZE * 18, TILE_SIZE * 5);
        droid3 = FXGL.spawn("droid3",TILE_SIZE * 18, TILE_SIZE * 20);

        cyborg = FXGL.spawn("cyborg");

        itemLife = FXGL.spawn("itemLife", TILE_SIZE * 19,TILE_SIZE * 10);
        itemLife = FXGL.spawn("itemLife", TILE_SIZE * 3,TILE_SIZE * 13);


        itemAtack = FXGL.spawn("itemAtack",TILE_SIZE * 9,TILE_SIZE * 4);
        itemAtack = FXGL.spawn("itemAtack",TILE_SIZE * 19, TILE_SIZE * 15);

        //Components
        //boss.addComponent(new EnemyController(cyborg,combatModeUI, TILE_SIZE));
        input.movInput(cyborg,combatModeUI);
    }

    protected void initUI()
    {
        Button btnMenu = new Button();
        btnMenu.setTranslateX(750); // Ajusta la posición X según tu diseño
        btnMenu.setTranslateY(10);  // Ajusta la posición Y según tu diseño

        btnMenu.setOnAction(e -> FXGL.getGameController().gotoGameMenu());

        FXGL.addUINode(btnMenu);




        ui.showUI();
        combatModeUI.showCombatUI();
       // combatModeUI.setHealthEnemi(droid2.getComponent(CombatStatsComponent.class).getMaxHealth(),droid2);
       // combatModeUI.setHealthEnemi(droid1.getComponent(CombatStatsComponent.class).getMaxHealth(),droid1);
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