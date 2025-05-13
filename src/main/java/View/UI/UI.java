package View.UI;

import App.Components.CombatStatsComponent;
import App.Services.MusicService;
import Domain.Entity.Characters.Players.Cyborg;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class UI {

    public UI(){
    }
    //Images
    Image wikiImage;
    Image buttonWikiImage;
    Image buttonGameMenuImage;
    Image buttonMainMenuImage;
    Image statBar;
    Image statAtackBar;
    Image specialPointsBarImage;

    //Image SteepPointPlayer;
    //Image barraPasosPlayer;
    //Image barraPasosEnemigo;
    //Image puntosPasosEnemigo;

    Text nameCharaterText;
    Text porcentAtackText;


    //ImagesView
    ImageView buttonWikiView;
    ImageView wikiView;
    ImageView buttonGameMenuView;
    ImageView buttonMainMenuView;
    ImageView specialPointBarView;
    ImageView heartBarImageViewPlayer;
    ImageView statAtackBarView;
    ImageView statBarView;

    //Buttons
    Button buttonWiki;
    Button gameMenuButton;
    Button mainMenuButton;

    //Hbox
    //public HBox playerSteepHbox;
    //public HBox enemyStepsHbox;

    public void showUI()
    {

        //Caja de informacion
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
        buttonGameMenuImage = getAssetLoader().loadImage("botonGameMenu.png");
        buttonMainMenuImage = getAssetLoader().loadImage("buttonMainMenu.png");
        buttonWikiImage = getAssetLoader().loadImage("wikiButton.png");
        specialPointsBarImage = getAssetLoader().loadImage("specialPointBar.png");
        statBar = getAssetLoader().loadImage("statBar.png");
        statAtackBar = getAssetLoader().loadImage("statAtackBar.png");

        //barraPasosPlayer = getAssetLoader().loadImage("barra_pasos.png");
        //SteepPointPlayer = getAssetLoader().loadImage("pasos_point.png");
        //barraPasosEnemigo = getAssetLoader().loadImage("barra_pasos_ememigo.png");
        //puntosPasosEnemigo = getAssetLoader().loadImage("pasos_point_enemi.png");

        //wiki
        buttonWikiView = new ImageView();
        buttonWikiView.setImage(buttonWikiImage);
        wikiImage =getAssetLoader().loadImage("wiki_image.png");
        wikiView = new ImageView();
        wikiView.setImage(wikiImage);
        wikiView.setTranslateX(TILE_SIZE * 8);
        wikiView.setTranslateY(TILE_SIZE * 5);
        buttonWiki = new Button();
        buttonWiki.setPrefSize(70,70);
        buttonWiki.setGraphic(buttonWikiView);
        buttonWiki.setTranslateX(TILE_SIZE);
        buttonWiki.setTranslateY(TILE_SIZE * 4 - 10);
        buttonWiki.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        //Special Point Bar
        specialPointBarView = new ImageView();
        specialPointBarView.setImage(specialPointsBarImage);
        specialPointBarView.setFitWidth(245);
        specialPointBarView.setFitHeight(50);
        specialPointBarView.setTranslateY(TILE_SIZE * 15 - 3);
        specialPointBarView.setTranslateX(TILE_SIZE * 3 + 5);

        //Stat Bar
        statBarView = new ImageView();
        statBarView.setImage(statBar);
        statAtackBarView = new ImageView();
        statAtackBarView.setImage(statAtackBar);
        statAtackBarView.setTranslateX(TILE_SIZE * 7);
        statAtackBarView.setTranslateY(TILE_SIZE * 16);
        statBarView.setTranslateX(TILE_SIZE);
        statBarView.setTranslateY(TILE_SIZE * 15 - 10);

        //Button GameMenu
        buttonGameMenuView = new ImageView();
        buttonGameMenuView.setImage(buttonGameMenuImage);
        gameMenuButton = new Button();
        gameMenuButton.setPrefSize(70,70);
        gameMenuButton.setGraphic(buttonGameMenuView);
        gameMenuButton.setTranslateX(TILE_SIZE);
        gameMenuButton.setTranslateY(TILE_SIZE);
        gameMenuButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        //Button MainMenu
        buttonMainMenuView = new ImageView();
        buttonMainMenuView.setImage(buttonMainMenuImage);
        mainMenuButton = new Button();
        mainMenuButton.setPrefSize(70,70);
        mainMenuButton.setGraphic(buttonMainMenuView);
        mainMenuButton.setTranslateX(TILE_SIZE);
        mainMenuButton.setTranslateY(TILE_SIZE * 3 - 30);
        mainMenuButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

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

        gameMenuButton.setOnAction(e -> {
            FXGL.getGameController().gotoGameMenu();
            MusicService.playKey();
        });
        buttonWiki.setOnAction(e -> {
            setImageWiki();
            MusicService.playKey();
        });
        mainMenuButton.setOnAction(e -> {
            FXGL.getGameController().gotoMainMenu();
            MusicService.playKey();
        });

        getGameScene().addUINode(buttonWiki);
        getGameScene().addUINode(gameMenuButton);
        getGameScene().addUINode(mainMenuButton);
        getGameScene().addUINode(specialPointBarView);
        getGameScene().addUINode(statAtackBarView);
        getGameScene().addUINode(statBarView);
        getGameScene().addUINode(nameCharaterText);
        getGameScene().addUINode(porcentAtackText);

        //getGameScene().addUINode(barraPasosPlayerView);
        //etGameScene().addUINode(playerSteepHbox);
        //getGameScene().addUINode(barraPasosEnemigoView);
        //getGameScene().addUINode(enemyStepsHbox);
    }

    private void setImageWiki(){
        getGameScene().addUINode(wikiView);

        wikiView.addEventFilter(MouseEvent.MOUSE_PRESSED, MouseEvent::consume);

        Scene scene = getGameScene().getRoot().getScene();
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            // Si la wikiView sigue en la UI, la quitamos
            if (getGameScene().getUINodes().contains(wikiView)) {
                getGameScene().removeUINode(wikiView);
            }
        });
    }

    public void atackBar(int amount, Entity player){
        int total = player.getComponent(CombatStatsComponent.class).atacck += amount;
        porcentAtackText.setText("Atacck +" + total );
    }
}
