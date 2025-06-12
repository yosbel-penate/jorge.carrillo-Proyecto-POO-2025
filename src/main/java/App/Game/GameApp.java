package App.Game;

import App.Components.CombatStatsComponent;
import App.Services.CollitionServices.CollitionService;
import App.Services.MusicService;
import View.UI.*;
import App.EntityFactory.EnemyFactory;
import App.EntityFactory.ObjectFactory;
import App.EntityFactory.PlayersFactory;
import App.Services.Input;
import Domain.Entity.Types;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Domain.Settings.SettingsGame.*;
import static View.UI.UI.borderEntityIdentifier;
import static View.UI.UI.botonStatus;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

//AL EXTENDER DE GAMEAPPLICATION LE INDICAMOS AL FRAMEWORK QUE ESTA ES LA CLASE PRINCIPAL DEL JUEGO
public class GameApp extends GameApplication
{

    //Entidades
    public static Entity zara;
    public static Entity JaxKane;
    public static Entity toxic;
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

    //ARRAY LIST CON LOS PERSONAJES ACTUALMENTE JUGABLES
    public static ArrayList<Entity> playersSelected = new ArrayList<>();

    LevelManager levelManager = new LevelManager();
    static Input input = new Input();
    static UI ui = new UI();
    private Board board = new Board();
    static CombatModeUI combatModeUI = new CombatModeUI(ui);
    private final CollitionService collitionService = new CollitionService(input, combatModeUI);

    @Override
    protected void initSettings(GameSettings settings) {

        settings.setAppIcon("iconInit.png");
        settings.setDefaultCursor(new CursorInfo("cursor.png", 0, 0));
        settings.setMainMenuEnabled(true);
        //CARGO LOS MENUS DE PAUSA Y DE INICIO
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
        //DEFINE LA RESOLUCION DE LA PANTALLA DE JUEGO
        settings.setWidth(1536);
        settings.setHeight(1024);
        settings.setTitle(SettingsGame.gameTitle);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
    vars.put("coins", 0); // otra variable
}

    @Override
    protected void initGame() {

        //CARGA EL TABLERO DE JUEGO
        Board.boardTable(NUM_TILES_Y, NUM_TILES_X, TILE_SIZE);

        addEntitiesFactories();

        //Music
        MusicService.stopMainMenu();
        MusicService.playLevel1Music();

        //Level Loader
        levelManager.loadLevel("level_01");
        spawnLevel_01Entities();

        findPanels();

        initCollitionSeervices();

        input.movInput();
    }

    private void findPanels() {
    // Busca en el mundo de juego todas las entidades tipo PANEL
    List<Entity> paneles = getGameWorld().getEntitiesByType(Types.EntityType.PANEL);

    if (paneles.size() >= 2) {
        panel = paneles.get(0);
        panel2 = paneles.get(1);
        System.out.println("Paneles encontrados y asignados.");
    } else {
        System.err.println("No se encontraron suficientes paneles, se encontraron " + paneles.size());
    }
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

        //Inicializamos las colisiones con las diferentes entidades del juego
        collitionService.starDoorCollition(doorLevel1);
        collitionService.startCollitionCoin(combatModeUI);
        collitionService.startCollitionEnemy(combatModeUI);
        collitionService.startCollitionItemAtack(ui);
        collitionService.startCollitionItemLife(combatModeUI);
        collitionService.startCollitionItemSpecialPoint(combatModeUI);
        collitionService.startCollitionBarrier(combatModeUI);
  
        Map<Entity, Entity> panelBarrierMap = new HashMap<>();
        panelBarrierMap.put(panel, barrier);
        panelBarrierMap.put(panel2, barrier1);

        collitionService.startPanelCollision(panelBarrierMap, combatModeUI);
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
        if (playersSelected.isEmpty()){
            System.out.println("se acabo");
        }
        Entity lastEntityWithBorde = null;
        if (botonStatus && GameApp.currentEntity != null && GameApp.currentEntity != lastEntityWithBorde) {
            borderEntityIdentifier();
            lastEntityWithBorde = GameApp.currentEntity;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}