package View.UI;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import App.Game.CollitionService;
import App.Game.MusicService;
import Domain.Entity.Characters.Enemies.Drone;
import Domain.Entity.Characters.Players.Cyborg;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import static Domain.Settings.SettingsGame.TILE_SIZE;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class CombatModeUI {

    //Images
    Image lifePointPlayer;
    Image heartBarPlayer;
    Image panelCombat;
    Image atackImage;
    Image lifePointEnemi;
    Image heartBarEnemi;

    //ImagesView
    ImageView panelCombatView;
    ImageView atackView;
    ImageView heartBarImageViewEnemi;
    ImageView heartBarImageViewPlayer;

    //Hboxs
    public HBox heartsBarEnemiHbox;
    public HBox heartsBarPlayerHbox;

    //Buttons
    Button buttonAtack;

    //Instances
    private UI ui;
    //CollitionService collitionService;

    //Vars
    public boolean statusbuttonAtacck = true;
    private PauseTransition enemyTurnDelay = new PauseTransition(Duration.seconds(1));

    //Contructores
    public CombatModeUI(UI ui){
        this.ui = ui;
        //this.collitionService = collitionService;
    }
    public CombatModeUI(){}

    public void showCombatUI(){
        //Cargadores de imagenes
        heartBarEnemi = getAssetLoader().loadImage("barra_enemigo.png");
        lifePointEnemi = getAssetLoader().loadImage("life_point_enemi.png");
        heartBarPlayer = getAssetLoader().loadImage("life_bar.png");
        lifePointPlayer = getAssetLoader().loadImage("life_point.png");
        atackImage = getAssetLoader().loadImage("button_atacck.png");
        panelCombat = getAssetLoader().loadImage("combat_panel.png");

        //=============ImagesViews=============
        //Barra de vida del Jugador
        heartBarImageViewPlayer = new ImageView();
        heartBarImageViewPlayer.setImage(heartBarPlayer);
        heartBarImageViewPlayer.setTranslateX(TILE_SIZE);
        heartBarImageViewPlayer.setTranslateY(TILE_SIZE * 17);

        //=========Barra de vida del Enemigo==========
        heartBarImageViewEnemi = new ImageView();
        heartBarImageViewEnemi.setImage(heartBarEnemi);
        heartBarImageViewEnemi.setTranslateX(TILE_SIZE * 21);
        heartBarImageViewEnemi.setTranslateY(TILE_SIZE * 17);

        //=======Panel Jugador============
        panelCombatView = new ImageView();
        panelCombatView.setImage(panelCombat);

        //=============Boton de Ataque basico===========
        buttonAtack = new Button();
        buttonAtack.setPrefSize(100, 100);
        atackView = new ImageView();
        atackView.setImage(atackImage);
        atackView.setFitHeight(100);
        atackView.setFitWidth(100);
        buttonAtack.setGraphic(atackView);
        buttonAtack.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        heartsBarEnemiHbox = new HBox(5);
        heartsBarEnemiHbox= new HBox(5);
        for (int i = 0; i < 10; i++) {
            ImageView heartViewEnemi = new ImageView(lifePointEnemi);
            // Opcional: ajustar tamaño de cada imagen
            heartViewEnemi.setFitWidth(20);
            heartViewEnemi.setFitHeight(32);
            heartsBarEnemiHbox.getChildren().add(heartViewEnemi);
        }
        heartsBarEnemiHbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);



        //=========Barra de vida de Jugadores===============
        heartsBarPlayerHbox = new HBox(5); // Espaciado de 5 píxeles entre corazones.

        for (int i = 0; i < Cyborg.life; i++) {
            ImageView heartView = new ImageView(lifePointPlayer);
            // Opcional: ajustar tamaño de cada imagen
            heartView.setFitWidth(20);
            heartView.setFitHeight(32);
            heartsBarPlayerHbox.getChildren().add(heartView);
        }
        heartsBarPlayerHbox.setTranslateX(TILE_SIZE + 12);
        heartsBarPlayerHbox.setTranslateY(TILE_SIZE * 17 + 13);

        getGameScene().addUINode(heartBarImageViewPlayer);
        getGameScene().addUINode(heartsBarPlayerHbox);
    }

    public void combatModeStart(boolean combatModeStatus , Entity player,Entity enemy) {

        if (combatModeStatus){

            HBox newBar= new HBox(5);
            for (int i = 0; i < enemy.getComponent(CombatStatsComponent.class).getMaxHealth(); i++) {
                ImageView heartViewEnemi = new ImageView(lifePointEnemi);
                // Opcional: ajustar tamaño de cada imagen
                heartViewEnemi.setFitWidth(20);
                heartViewEnemi.setFitHeight(32);
                newBar.getChildren().add(heartViewEnemi);
            }
            newBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            newBar.setTranslateX(TILE_SIZE * 28 + 24);
            newBar.setTranslateY(TILE_SIZE * 17 + 12);

            this.heartsBarEnemiHbox = newBar;

            //agregando nodos graficos
            getGameScene().addUINode(heartBarImageViewEnemi);
            getGameScene().addUINode(panelCombatView);
            getGameScene().addUINode(newBar);


            panelCombatView.setTranslateX(TILE_SIZE);
            panelCombatView.setTranslateY(TILE_SIZE * 10);

            buttonAtack.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (statusbuttonAtacck){
                        startAtacckPLayer(player, enemy);
                    }
                }
            });

            //boton
            getGameScene().addUINode(buttonAtack);
            buttonAtack.setTranslateX(TILE_SIZE * 3);
            buttonAtack.setTranslateY(TILE_SIZE * 10 + 10);

        } else {
            getGameScene().removeUINode(panelCombatView);
            getGameScene().removeUINode(buttonAtack);
        }
    }

    public void startAtacckPLayer(Entity player, Entity enemy){
        statusbuttonAtacck = false;
        player.getComponent(AnimationComponents.class).playAttackAnimation();
        reduceHealthEnemi(Cyborg.atack,enemy);
        MusicService.playWeanpon();

        if (enemy.getComponent(CombatStatsComponent.class).getCurrentHealth() <= 0) {
            enemyDead(enemy);//animacion de muerte del enemigo
            MusicService.playDeat();

            // Remover enemigo y finalizar combate después de 2 segundos
            FXGL.runOnce(() -> {
                statusbuttonAtacck = true;
                enemy.removeFromWorld();
                getGameScene().removeUINode(panelCombatView);
                getGameScene().removeUINode(buttonAtack);
                getGameScene().removeUINode(heartsBarEnemiHbox);
                getGameScene().removeUINode(heartBarImageViewEnemi);
                MusicService.stopBattleMusic();
                MusicService.playLevel1Music();
            }, Duration.seconds(2));
        }else {
            // Turno enemigo después de 1 segundo
            enemyTurnDelay.setOnFinished(ev -> startAtacckEnemy(player, enemy));
            enemyTurnDelay.play();
        }
    }

    public void startAtacckEnemy(Entity player, Entity enemy){
        if (!statusbuttonAtacck){
            statusbuttonAtacck = true;
            enemy.getComponent(AnimationComponents.class).playAttackAnimation();
            MusicService.playWeanponEnemy();
            reduceHealthPlayer(enemy.getComponent(CombatStatsComponent.class).getAtacck(),player);

            FXGL.runOnce(() -> {
                if (player.getComponent(CombatStatsComponent.class).getCurrentHealth() <= 0) {
                    //Logica de muerte para el player
                    //collitionService.combatMode(false);
                }
            }, Duration.seconds(0.5));
        }
    }

    public void enemyDead(Entity enemy){
        enemy.getComponent(AnimationComponents.class).playDeatAnimation();
    }

    public void updateHealthBarPlayer(Entity player) {
        // 1. Obtener el componente una sola vez
        CombatStatsComponent stats = player.getComponent(CombatStatsComponent.class);

        // 2. Verificar que existe el componente
        if (stats == null) {
            throw new IllegalStateException("El jugador no tiene CombatStatsComponent");
        }

        // 3. Asegurar que el HBox tiene suficientes corazones
        int maxHearts = stats.getMaxHealth();
        if (heartsBarPlayerHbox.getChildren().size() != maxHearts) {
            initializeHearts(maxHearts); // Método para crear los corazones
        }

        // 4. Actualizar visibilidad
        for (int i = 0; i < maxHearts; i++) {
            ImageView heartView = (ImageView) heartsBarPlayerHbox.getChildren().get(i);
            heartView.setVisible(i < stats.currentHealth);
        }
    }

    // Método nuevo para inicializar los corazones
    private void initializeHearts(int maxHearts) {
        heartsBarPlayerHbox.getChildren().clear();

        for (int i = 0; i < maxHearts; i++) {
            ImageView heart = new ImageView(lifePointPlayer);
            heart.setFitWidth(20);  // Ajustar al tamaño deseado
            heart.setFitHeight(32);
            heart.setVisible(false); // Inicialmente ocultos
            heartsBarPlayerHbox.getChildren().add(heart);
        }
    }

    public void updateHealthBarEnemi(Entity enemy) {
        for (int i = 0; i < enemy.getComponent(CombatStatsComponent.class).getMaxHealth(); i++) {
            ImageView heartView = (ImageView) heartsBarEnemiHbox.getChildren().get(i);
            if (i < enemy.getComponent(CombatStatsComponent.class).currentHealth) {
                heartView.setVisible(true);
                // Opcional: resetear la imagen, por si se modificó
                heartView.setImage(lifePointEnemi);
            } else {
                heartView.setVisible(false);
            }
        }
    }

    public void setHealthEnemi(int health,Entity enemy) {
        enemy.getComponent(CombatStatsComponent.class).currentHealth = Math.max(0, Math.min(health,enemy.getComponent(CombatStatsComponent.class).getMaxHealth()));
        updateHealthBarEnemi(enemy);
    }

    public void setHealthPLayer(int health,Entity player) {
        player.getComponent(CombatStatsComponent.class).currentHealth = Math.max(0, Math.min(health, player.getComponent(CombatStatsComponent.class).getMaxHealth()));
        updateHealthBarPlayer(player);
    }

    public void reduceHealthPlayer(int amount,Entity player) {
        player.getComponent(CombatStatsComponent.class).currentHealth -= amount;
        if (player.getComponent(CombatStatsComponent.class).currentHealth< 0)
            player.getComponent(CombatStatsComponent.class).currentHealth = 0;
        updateHealthBarPlayer(player);
    }

    public void reduceHealthEnemi(int amount,Entity enemy) {
        enemy.getComponent(CombatStatsComponent.class).currentHealth -= amount;
        if (enemy.getComponent(CombatStatsComponent.class).currentHealth < 0)
            enemy.getComponent(CombatStatsComponent.class).currentHealth = 0;
        updateHealthBarEnemi(enemy);
    }

    /*

    public void reduceStepPoint() {
        if (combatStats.numeroActualPasos < combatStats.setPLayerInitSteeps()) {
            // Se obtiene el ImageView correspondiente al paso a descontar
            ImageView pasoIcon = (ImageView) playerSteepHbox.getChildren().get(combatStats.numeroActualPasos);
            // Se puede optar por ocultarlo o cambiarle la opacidad para dar efecto de "gastado"
            pasoIcon.setVisible(false);
            // Alternativamente, si no deseas ocultar por completo puedes reducir la opacidad:
            // pasoIcon.setOpacity(0.3);
            combatStats.numeroActualPasos++;
        }
    }

    public void reduceEnemyStepPoint() {
        if (combatStats.enemyConsumedSteps < combatStats.enemyMaxSteps) {
            // Obtén el ícono correspondiente a ese paso y cambia su visibilidad o su opacidad
            ImageView enemyStepIcon = (ImageView) ui.enemyStepsHbox.getChildren().get(combatStats.enemyConsumedSteps);
            enemyStepIcon.setVisible(false); // O, alternativamente: enemyStepIcon.setOpacity(0.3);
            combatStats.enemyConsumedSteps++;
        }
    }

    */

    public void disableCombatMode(boolean combatMode){
        combatMode = false;
    }

}
