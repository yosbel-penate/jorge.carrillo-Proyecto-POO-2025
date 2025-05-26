package App.Services.CollitionServices;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import App.Game.LevelManager;
import App.Services.Input;
import App.Services.MusicService;
import Domain.Entity.Types;
import View.Maps.Maps;
import View.UI.CombatModeUI;
import View.UI.UI;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class CollitionService
{
    //Instancias
    public Input inputController;
    public boolean combatMode;
    CombatModeUI combatModeUI;

    //Contructores
    public CollitionService(){}
    public CollitionService(Input inputController) {
        this.inputController = inputController;
    }

    //Vars
    private Entity currentEnemy = null;
    private boolean panelEnable = false;
    private Entity lastBarrier = null;

    //Manejo de colisiones con elementos estaticos del entorno
    public void startCollitionBarrier(CombatModeUI combatModeUI){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.BARRIER)
        {

        });
    }

    public void starPanelCollition(Entity barrier){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.PANEL)
        {
            @Override
            protected void onCollisionBegin(Entity player, Entity item){

                if (!panelEnable || barrier != lastBarrier){
                    panelEnable = true;
                    item.getComponent(AnimationComponents.class).playChangesPanel();
                    MusicService.playPanel();
                    Point2D itemPosition = barrier.getPosition();
                    barrier.getComponent(AnimationComponents.class).playDisabledBarrier();
                    FXGL.getGameTimer().runOnceAfter(
                            () -> barrier.removeFromWorld(),
                            Duration.seconds(1)

                    );
                    FXGL.spawn("barrierDisabled",itemPosition);
                }
                lastBarrier = barrier;
            }
        });
    }

    LevelManager levelManager = new LevelManager();

    private void clearAllEntities() {
        FXGL.getGameWorld()
                // obtenemos una copia de la lista para evitar ConcurrentModificationException
                .getEntitiesCopy()
                // removemos cada entidad del mundo
                .forEach(Entity::removeFromWorld);
    }

    public void starDoorCollition(Entity door) {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                // Reproducir la animación
                door.getComponent(AnimationComponents.class).playChangesDoor();

                // Esperar 1 segundo (1000 ms) antes de cargar el nivel
                FXGL.getGameTimer().runOnceAfter(() -> {
                    clearAllEntities();
                    GameApp.playersSelected.clear();
                    levelManager.loadLevel("level_02");
                }, Duration.seconds(1.5));
            }
        });
    }

    public void startCollitionItemSpecialPoint(CombatModeUI combatModeUI){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.ITEM_SPECIAL_POINT)
        {
            @Override
            protected void onCollision(Entity player, Entity item){
                item.removeFromWorld();
                MusicService.playItem();

                CombatStatsComponent stat = player.getComponent(CombatStatsComponent.class);

                stat.currentSpecialPoints = Math.min(stat.currentSpecialPoints + 1,stat.getSpecialPoints());

                combatModeUI.updateSpecialPointBarPLayer(player);
            }
        });
    }

    public void startCollitionItemLife(CombatModeUI combatModeUI){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.ITEM_LIFE)
        {
            @Override
            protected void onCollision(Entity player, Entity item){
                item.removeFromWorld();
                MusicService.playItem();

                // 1. Obtener el componente de estadísticas
                CombatStatsComponent stats = player.getComponent(CombatStatsComponent.class);

                // 2. Aumentar la vida (ej: +1 corazón)
                stats.currentHealth = Math.min(stats.currentHealth + 1, stats.getMaxHealth());

                // 3. Actualizar la UI con el nuevo valor
                combatModeUI.updateHealthBarPlayer(player);
            }
        });
    }

    public void startCollitionCoin(CombatModeUI combatModeUI){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.COIN)
        {
            @Override
            protected void onCollision(Entity player, Entity item){
                item.removeFromWorld();
                MusicService.playCoin();
                UI.updateAmountCoins(1);
            }
        });
    }

    public void startCollitionItemAtack(UI ui){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.ITEM_ATACCK)
        {
            @Override
            protected void onCollision(Entity player, Entity item){
                item.removeFromWorld();
                MusicService.playItem();
                ui.atackBar(1,player);
            }
        });
    }

    public void startCollitionPlayers(UI ui){
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.PLAYER)
        {
            @Override
            protected void onCollision(Entity player, Entity item){

            }
        });
    }

    public void startCollitionEnemy(CombatModeUI combatModeUI) {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.PLAYER,
                Types.EntityType.ENEMY)
        {
            @Override
            protected void onCollisionBegin(Entity player, Entity enemy) {
                initCollition(player,enemy,combatModeUI);
            }

            @Override
            protected void onCollision(Entity player, Entity enemy) {
            }

            @Override
            protected void onCollisionEnd(Entity player,Entity enemy) {
                endCollition(player,enemy);
            }
        });
    }

    public void initCollition(Entity player, Entity enemy, CombatModeUI combatModeUI){
        if(combatMode || currentEnemy != enemy) {
            currentEnemy = enemy;
            combatMode = true;

            inputController.setCanMove(false);
            combatModeUI.combatModeStart(combatMode,player,enemy);
            MusicService.stopLevel1();
            MusicService.battleMusic();
        }
    }

    public void endCollition(Entity player, Entity enemy){
        combatMode = false;
        currentEnemy = null;
        inputController.setCanMove(true);
        MusicService.playLevel1Music();
        // 1. Guardamos la posición del enemigo
        Point2D pos = enemy.getPosition();


        // 3. Hacemos spawn de la nueva entidad en esa misma posición
        //    "NewEnemy" debe estar registrado en initSettings() con addEntityFactory()
        FXGL.spawn("coin",pos.getX() + 10,pos.getY() + 10);
        MusicService.playCoin();
    }

}
