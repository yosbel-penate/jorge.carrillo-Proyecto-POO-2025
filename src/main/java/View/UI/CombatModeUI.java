package View.UI;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import App.Services.MusicService;
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
    Image statBarEnemyImage;
    Image specialPointImage;
    Image lifePointPlayer;
    Image heartBarPlayer;
    Image panelCombat;
    Image atackBasicButtonImage;
    Image lifePointEnemi;
    Image heartBarEnemi;
    Image atackSpecialButtonImage;

    //ImagesView
    ImageView statBarEnemyView;
    ImageView atackSpeciaButtonView;
    ImageView panelCombatView;
    ImageView atackBasicButtonView;
    ImageView heartBarImageViewEnemi;
    ImageView heartBarImageViewPlayer;

    //Hboxs
    public HBox specialPointsHbox;
    public HBox heartsBarEnemiHbox;
    public HBox heartsBarPlayerHbox;

    //Buttons
    Button buttonAtackSpecial;
    Button buttonAtackBasic;

    //Instances
    private UI ui;

    //Vars
    private String tipoOfAnimationInButton;
    public boolean statusbuttonAtacck = true;
    private PauseTransition enemyTurnDelay = new PauseTransition(Duration.seconds(1));

    //Contructores
    public CombatModeUI(UI ui){
        this.ui = ui;
    }
    public CombatModeUI(){}

    public void showCombatUI(Entity player){

        //Cargadores de imagenes
        specialPointImage = getAssetLoader().loadImage("specialPoint.png");
        atackSpecialButtonImage = getAssetLoader().loadImage("special.png");
        heartBarEnemi = getAssetLoader().loadImage("barra_enemigo.png");
        lifePointEnemi = getAssetLoader().loadImage("life_point_enemi.png");
        heartBarPlayer = getAssetLoader().loadImage("life_bar.png");
        lifePointPlayer = getAssetLoader().loadImage("life_point.png");
        atackBasicButtonImage = getAssetLoader().loadImage("button_atacck.png");
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
        heartBarImageViewEnemi.setTranslateX(TILE_SIZE * 20);
        heartBarImageViewEnemi.setTranslateY(TILE_SIZE * 17);

        //=======Panel Jugador============
        panelCombatView = new ImageView();
        panelCombatView.setImage(panelCombat);

        //===========Boton de Ataque Especial===========
        buttonAtackSpecial = new Button();
        buttonAtackSpecial.setPrefSize(100,100);
        atackSpeciaButtonView = new ImageView();
        atackSpeciaButtonView.setImage(atackSpecialButtonImage);
        buttonAtackSpecial.setGraphic(atackSpeciaButtonView);
        buttonAtackSpecial.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        //=============Boton de Ataque basico===========
        buttonAtackBasic = new Button();
        buttonAtackBasic.setPrefSize(100, 100);
        atackBasicButtonView = new ImageView();
        atackBasicButtonView.setImage(atackBasicButtonImage);
        atackBasicButtonView.setFitHeight(100);
        atackBasicButtonView.setFitWidth(100);
        buttonAtackBasic.setGraphic(atackBasicButtonView);
        buttonAtackBasic.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        //Barra de Puntos Especiales
        specialPointsHbox = new HBox(5);
        for (int i = 0; i < player.getComponent(CombatStatsComponent.class).getSpecialPoints(); i ++){
            ImageView specialPointView = new ImageView(specialPointImage);
            specialPointView.setFitHeight(30);
            specialPointView.setFitWidth(72);
            specialPointsHbox.getChildren().add(specialPointView);
        }
        specialPointsHbox.setTranslateY(TILE_SIZE * 15 + 3);
        specialPointsHbox.setTranslateX(TILE_SIZE * 4 - 37);

        //Barra de vida de Enemigos
        heartsBarEnemiHbox = new HBox(5);
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

        for (int i = 0; i < player.getComponent(CombatStatsComponent.class).getMaxHealth(); i++) {
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
        getGameScene().addUINode(specialPointsHbox);
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
            newBar.setTranslateX(TILE_SIZE * 27 + 24);
            newBar.setTranslateY(TILE_SIZE * 17 + 12);
            this.heartsBarEnemiHbox = newBar;

            panelCombatView.setTranslateX(TILE_SIZE);
            panelCombatView.setTranslateY(TILE_SIZE * 10);

            initButtonAtacckBasic(player,enemy);
            initButtonAtacckSpecial(player, enemy);

            getGameScene().addUINode(heartBarImageViewEnemi);
            getGameScene().addUINode(panelCombatView);
            getGameScene().addUINode(newBar);
            getGameScene().addUINode(buttonAtackSpecial);
            buttonAtackSpecial.setTranslateX(TILE_SIZE * 5);
            buttonAtackSpecial.setTranslateY(TILE_SIZE * 10 + 10);
            getGameScene().addUINode(buttonAtackBasic);
            buttonAtackBasic.setTranslateX(TILE_SIZE * 3);
            buttonAtackBasic.setTranslateY(TILE_SIZE * 10 + 10);

        } else {
            getGameScene().removeUINode(panelCombatView);
            getGameScene().removeUINode(buttonAtackBasic);
        }
    }

    public void initButtonAtacckBasic(Entity player, Entity enemy){
        buttonAtackBasic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (statusbuttonAtacck){
                    tipoOfAnimationInButton = "basic";
                    startAtacckPLayer(player, enemy, tipoOfAnimationInButton);
                    UI.animacionPresionarBoton(buttonAtackBasic);
                }
            }
        });
    }

    public void initButtonAtacckSpecial(Entity player, Entity enemy){
        if (player.getComponent(CombatStatsComponent.class).getCurrentSpecialPoints() > 0){
            buttonAtackSpecial.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (player.getComponent(CombatStatsComponent.class).currentSpecialPoints > 0){
                        buttonAtackSpecial.setDisable(false);
                    }
                    if (statusbuttonAtacck){
                        tipoOfAnimationInButton = "special";
                        reduceSpecialPoint(player);
                        startAtacckPLayer(player, enemy, tipoOfAnimationInButton);
                        UI.animacionPresionarBoton(buttonAtackSpecial);

                    }
                }
            });
        }else {
            buttonAtackSpecial.setDisable(true);
        }

    }

    public void startAtacckPLayer(Entity player, Entity enemy, String tipoOfAnimationInButton){
        statusbuttonAtacck = false;
        switch (tipoOfAnimationInButton){
            case "basic" :
                player.getComponent(AnimationComponents.class).playAttackAnimation();
                reduceHealthEnemi(player.getComponent(CombatStatsComponent.class).getAtacck(),enemy);
                MusicService.playWeanpon();
                break;
            case "special" :
                player.getComponent(AnimationComponents.class).playSpecialAnimation();
                reduceHealthEnemi(player.getComponent(CombatStatsComponent.class).getAtacck(),enemy);
                MusicService.playWeanpon();
                break;
        }

        if (enemy.getComponent(CombatStatsComponent.class).getCurrentHealth() <= 0) {
            enemyDead(enemy);//animacion de muerte del enemigo
            MusicService.playDeat();

            // Remover enemigo y finalizar combate después de 2 segundos
            FXGL.runOnce(() -> {
                statusbuttonAtacck = true;
                enemy.removeFromWorld();
                getGameScene().removeUINode(panelCombatView);
                getGameScene().removeUINode(buttonAtackBasic);
                getGameScene().removeUINode(heartsBarEnemiHbox);
                getGameScene().removeUINode(heartBarImageViewEnemi);
                getGameScene().removeUINode(buttonAtackSpecial);
                MusicService.stopBattleMusic();
                MusicService.playLevel1Music();
            }, Duration.seconds(1));
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
            reduceHealthPlayer(enemy.getComponent(CombatStatsComponent.class).getAtacck(),GameApp.currentEntity);

            FXGL.runOnce(() -> {
                if (GameApp.currentEntity.getComponent(CombatStatsComponent.class).getCurrentHealth() <= 0) {
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
        CombatStatsComponent stats = GameApp.currentEntity.getComponent(CombatStatsComponent.class);

        if (stats == null) {
            throw new IllegalStateException("El jugador no tiene CombatStatsComponent");
        }

        int maxHearts = stats.getMaxHealth();
        if (heartsBarPlayerHbox.getChildren().size() != maxHearts) {
            initializeHearts(maxHearts); // Método para crear los corazones
        }

        for (int i = 0; i < maxHearts; i++) {
            ImageView heartView = (ImageView) heartsBarPlayerHbox.getChildren().get(i);
            heartView.setVisible(i < stats.currentHealth);
        }
    }

    private void initializeHearts(int maxHearts) {
        heartsBarPlayerHbox.getChildren().clear();

        for (int i = 0; i < maxHearts; i++) {
            ImageView heart = new ImageView(lifePointPlayer);
            heart.setFitWidth(20);
            heart.setFitHeight(32);
            heart.setVisible(false);
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

    public void reduceSpecialPoint(Entity player){

        if (player.getComponent(CombatStatsComponent.class).currentSpecialPoints > 0){
            player.getComponent(CombatStatsComponent.class).currentSpecialPoints -= 1;
        }

        if (player.getComponent(CombatStatsComponent.class).currentSpecialPoints < 0)
            player.getComponent(CombatStatsComponent.class).currentSpecialPoints = 0;
        updateSpecialPointBarPLayer(player);
    }

    public void updateSpecialPointBarPLayer(Entity player){
        CombatStatsComponent stats = player.getComponent(CombatStatsComponent.class);
        int maxSP = stats.getSpecialPoints();
        int curSP = stats.getCurrentSpecialPoints();

        for (int i = 0; i < maxSP; i++) {
            ImageView spView = (ImageView) specialPointsHbox.getChildren().get(i);

            boolean active = (i < curSP);
            spView.setVisible(active);
            spView.setManaged(active);
            if (active) {
                spView.setImage(specialPointImage);
            }
        }
    }

    public void setHealthPLayer(int health,Entity player) {
        System.out.println(player.getComponent(CombatStatsComponent.class).name + "============================");
        GameApp.currentEntity.getComponent(CombatStatsComponent.class).currentHealth = Math.max(0, Math.min(health, GameApp.currentEntity.getComponent(CombatStatsComponent.class).getMaxHealth()));
        updateHealthBarPlayer(GameApp.currentEntity);
    }

    public void reduceHealthPlayer(int amount,Entity player) {
        GameApp.currentEntity.getComponent(CombatStatsComponent.class).currentHealth -= amount;
        if (GameApp.currentEntity.getComponent(CombatStatsComponent.class).currentHealth< 0)
            GameApp.currentEntity.getComponent(CombatStatsComponent.class).currentHealth = 0;
        updateHealthBarPlayer(GameApp.currentEntity);
    }

    public void reduceHealthEnemi(int amount,Entity enemy) {
        enemy.getComponent(CombatStatsComponent.class).currentHealth -= amount;
        if (enemy.getComponent(CombatStatsComponent.class).currentHealth < 0)
            enemy.getComponent(CombatStatsComponent.class).currentHealth = 0;
        updateHealthBarEnemi(enemy);
    }

}
