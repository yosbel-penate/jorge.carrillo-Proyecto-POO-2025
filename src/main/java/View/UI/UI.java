package View.UI;

import Domain.GameApp.LogicCombat;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class UI {

    //Images
    Image lifePointPlayer;
    Image lifePointEnemi;
    Image SteepPointPlayer;
    Image heartBarPlayer;
    Image heartBarEnemi;
    Image barraPasosPlayer;
    Image barraPasosEnemigo;
    Image puntosPasosEnemigo;

    //ImagesView
    ImageView barraPasosPlayerView;
    ImageView heartBarImageViewPlayer;
    ImageView heartBarImageViewEnemi;
    ImageView barraPasosEnemigoView;

    //Hbox
    public HBox heartsBarPlayerHbox;
    public HBox heartsBarEnemiHbox;
    public HBox playerSteepHbox;
    public HBox enemyStepsHbox;

    //=============Instancia de LogicCombat=============
    LogicCombat combatStats = new LogicCombat();

    public void showUI()
    {
        //=============ImagesLoader=============
        heartBarPlayer = getAssetLoader().loadImage("life_bar.png");
        heartBarEnemi = getAssetLoader().loadImage("barra_enemigo.png");
        lifePointPlayer = getAssetLoader().loadImage("life_point.png");
        lifePointEnemi = getAssetLoader().loadImage("life_point_enemi.png");
        barraPasosPlayer = getAssetLoader().loadImage("barra_pasos.png");
        SteepPointPlayer = getAssetLoader().loadImage("pasos_point.png");
        barraPasosEnemigo = getAssetLoader().loadImage("barra_pasos_ememigo.png");
        puntosPasosEnemigo = getAssetLoader().loadImage("pasos_point_enemi.png");


        //=============ImagesViews=============
        //Barra de vida del Jugador
        heartBarImageViewPlayer = new ImageView();
        heartBarImageViewPlayer.setImage(heartBarPlayer);
        heartBarImageViewPlayer.setTranslateX(TILE_SIZE);
        heartBarImageViewPlayer.setTranslateY(TILE_SIZE * 15);


        //Barra de vida del Enemigo
        heartBarImageViewEnemi = new ImageView();
        heartBarImageViewEnemi.setImage(heartBarEnemi);
        heartBarImageViewEnemi.setTranslateX(TILE_SIZE * 21);
        heartBarImageViewEnemi.setTranslateY(TILE_SIZE * 15);

        //Contador de pasos del Jugador
        barraPasosPlayerView = new ImageView();
        barraPasosPlayerView.setImage(barraPasosPlayer);
        barraPasosPlayerView.setTranslateX(TILE_SIZE);
        barraPasosPlayerView.setTranslateY(TILE_SIZE);

        //Contador de pasos del Enemigo
        barraPasosEnemigoView = new ImageView();
        barraPasosEnemigoView.setImage(barraPasosEnemigo);
        barraPasosEnemigoView.setTranslateX(TILE_SIZE * 22);
        barraPasosEnemigoView.setTranslateY(TILE_SIZE);


        //=========Barra de vida de Jugadores===============
        heartsBarPlayerHbox = new HBox(5); // Espaciado de 5 píxeles entre corazones.

        for (int i = 0; i < combatStats.setPlayerInitHealth(); i++) {
            ImageView heartView = new ImageView(lifePointPlayer);
            // Opcional: ajustar tamaño de cada imagen
            heartView.setFitWidth(32);
            heartView.setFitHeight(32);
            heartsBarPlayerHbox.getChildren().add(heartView);
        }
        heartsBarPlayerHbox.setTranslateX(TILE_SIZE + 12);
        heartsBarPlayerHbox.setTranslateY(TILE_SIZE * 15 + 13);


        //==========Barra de vida de Enemigos================
        heartsBarEnemiHbox = new HBox(5); // Espaciado de 5 píxeles entre corazones.

        for (int i = 0; i < combatStats.maxHealthEnemi; i++) {
            ImageView heartViewEnemi = new ImageView(lifePointEnemi);
            // Opcional: ajustar tamaño de cada imagen
            heartViewEnemi.setFitWidth(32);
            heartViewEnemi.setFitHeight(32);
            heartsBarEnemiHbox.getChildren().add(heartViewEnemi);
        }
        heartsBarEnemiHbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        heartsBarEnemiHbox.setTranslateX(TILE_SIZE * 29 + 25);
        heartsBarEnemiHbox.setTranslateY(TILE_SIZE * 15 + 12);


        //===============Barra de Pasos Jugadores=================
        playerSteepHbox = new HBox(5); // Espaciado de 5 píxeles entre corazones.

        for (int i = 0; i < combatStats.setPLayerInitSteeps(); i++) {
            ImageView pasosPointView = new ImageView(SteepPointPlayer);
            // Opcional: ajustar tamaño de cada imagen
            pasosPointView.setFitWidth(32);
            pasosPointView.setFitHeight(32);
            playerSteepHbox.getChildren().add(pasosPointView);
        }
        playerSteepHbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        playerSteepHbox.setTranslateX(TILE_SIZE + 14);
        playerSteepHbox.setTranslateY(TILE_SIZE + 11);

        //=============Barra de pasos del Enemigo===================
        enemyStepsHbox = new HBox(5); // Espaciado de 5 píxeles entre íconos.

        for (int i = 0; i < combatStats.enemyMaxSteps; i++) {
            ImageView enemyStepIcon = new ImageView(puntosPasosEnemigo);
            // Ajusta el tamaño si lo requieres
            enemyStepIcon.setFitWidth(32);
            enemyStepIcon.setFitHeight(32);
            enemyStepsHbox.getChildren().add(enemyStepIcon);
        }
        enemyStepsHbox.setTranslateX(TILE_SIZE * 27);
        enemyStepsHbox.setTranslateY(TILE_SIZE + 10);

        //Add Nodes
        getGameScene().addUINode(heartBarImageViewPlayer);
        getGameScene().addUINode(heartsBarPlayerHbox);
        getGameScene().addUINode(heartBarImageViewEnemi);
        getGameScene().addUINode(heartsBarEnemiHbox);
        getGameScene().addUINode(barraPasosPlayerView);
        getGameScene().addUINode(playerSteepHbox);
        getGameScene().addUINode(barraPasosEnemigoView);
        getGameScene().addUINode(enemyStepsHbox);
    }
}
