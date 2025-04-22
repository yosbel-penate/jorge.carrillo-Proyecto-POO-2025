package View.UI;

import Domain.Entity.Characters.Players.Cyborg;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class UI {

    //Images
    Image statBar;
    Image statAtackBar;

    //Image SteepPointPlayer;
    //Image barraPasosPlayer;
    //Image barraPasosEnemigo;
    //Image puntosPasosEnemigo;

    Text nameCharaterText;
    Text porcentAtackText;

    //ImagesView
    ImageView heartBarImageViewPlayer;
    ImageView statAtackBarView;
    ImageView statBarView;


    //Hbox
    //public HBox playerSteepHbox;
    //public HBox enemyStepsHbox;

    //=============Instancia de LogicCombat=============

    public void showUI()
    {


        porcentAtackText = new Text("Atacck +" + 2);
        porcentAtackText.setTranslateX(TILE_SIZE * 8);
        porcentAtackText.setTranslateY(TILE_SIZE * 16 + 23);
        porcentAtackText.setFill(Color.CYAN);
        porcentAtackText.setFont(Font.font("Negrita",20));

        nameCharaterText = new Text("Cyborg");
        nameCharaterText.setTranslateX(TILE_SIZE * 4);
        nameCharaterText.setTranslateY(TILE_SIZE * 16 + 27);
        nameCharaterText.setFill(Color.CYAN);
        nameCharaterText.setFont(Font.font("Negrita", 25));


        //=============ImagesLoader=============
        statBar = getAssetLoader().loadImage("statBar.png");
        statAtackBar = getAssetLoader().loadImage("statAtackBar.png");

        //barraPasosPlayer = getAssetLoader().loadImage("barra_pasos.png");
        //SteepPointPlayer = getAssetLoader().loadImage("pasos_point.png");
        //barraPasosEnemigo = getAssetLoader().loadImage("barra_pasos_ememigo.png");
        //puntosPasosEnemigo = getAssetLoader().loadImage("pasos_point_enemi.png");


        statBarView = new ImageView();
        statBarView.setImage(statBar);
        statAtackBarView = new ImageView();
        statAtackBarView.setImage(statAtackBar);

        statAtackBarView.setTranslateX(TILE_SIZE * 7);
        statAtackBarView.setTranslateY(TILE_SIZE * 16);

        statBarView.setTranslateX(TILE_SIZE);
        statBarView.setTranslateY(TILE_SIZE * 15 - 10);


        //Contador de pasos del Jugador
        //barraPasosPlayerView = new ImageView();
        //barraPasosPlayerView.setImage(barraPasosPlayer);
        //barraPasosPlayerView.setTranslateX(TILE_SIZE);
        //barraPasosPlayerView.setTranslateY(TILE_SIZE);

        //Contador de pasos del Enemigo
        //barraPasosEnemigoView = new ImageView();
        //barraPasosEnemigoView.setImage(barraPasosEnemigo);
        //barraPasosEnemigoView.setTranslateX(TILE_SIZE * 22);
        //barraPasosEnemigoView.setTranslateY(TILE_SIZE);




        /*

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

        */

        //Add Nodes
        getGameScene().addUINode(statAtackBarView);
        getGameScene().addUINode(statBarView);
        getGameScene().addUINode(nameCharaterText);
        getGameScene().addUINode(porcentAtackText);

        //getGameScene().addUINode(barraPasosPlayerView);
        //etGameScene().addUINode(playerSteepHbox);
        //getGameScene().addUINode(barraPasosEnemigoView);
        //getGameScene().addUINode(enemyStepsHbox);
    }

    public void atackBar(int amount){
        int total = Cyborg.atack += amount;
        porcentAtackText.setText("Atacck +" + total );
    }
}
