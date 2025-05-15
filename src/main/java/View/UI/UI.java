package View.UI;

import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import App.Services.MusicService;
import Domain.Entity.Characters.Players.Cyborg;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

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

    //Text
    Text nameCharaterText;
    Text porcentAtackText;

    //ImagesView
    ImageView buttonWikiView;
    ImageView wikiView;
    ImageView buttonGameMenuView;
    ImageView buttonMainMenuView;
    ImageView specialPointBarView;
    ImageView statAtackBarView;
    ImageView statBarView;

    //Buttons
    Button buttonWiki;
    Button gameMenuButton;
    Button mainMenuButton;

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


        //Botones del la interfaz principal
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

        //Hbox de identificadores de players



        HBox barraIdentificadores = new HBox(10);

        ArrayList<Button> listaBotones = new ArrayList<>();

        Image iconCyborg = getAssetLoader().loadTexture("iconCyborg.png").getImage();
        Image iconJaxKane = getAssetLoader().loadTexture("iconJaxKane.png").getImage();

        Button cyborgBtn = new Button();
        cyborgBtn.setGraphic(new ImageView(iconCyborg));
        cyborgBtn.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        //pintarBordeIcono("cyborg",cyborgBtn);
        cyborgBtn.setOnAction(e -> pintarBordeIcono("cyborg",cyborgBtn));

        Button jaxKaneBtn = new Button();
        jaxKaneBtn.setGraphic(new ImageView(iconJaxKane));
        jaxKaneBtn.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        //pintarBordeIcono("jaxKane",jaxKaneBtn);
        jaxKaneBtn.setOnAction(e -> pintarBordeIcono("jaxKane",jaxKaneBtn));

        listaBotones.add(jaxKaneBtn);
        listaBotones.add(cyborgBtn);

        barraIdentificadores.getChildren().addAll(cyborgBtn,jaxKaneBtn);

        barraIdentificadores.setTranslateX(TILE_SIZE * 26);
        barraIdentificadores.setTranslateY(TILE_SIZE );

        getGameScene().addUINode(barraIdentificadores);
        getGameScene().addUINode(buttonWiki);
        getGameScene().addUINode(gameMenuButton);
        getGameScene().addUINode(mainMenuButton);
        getGameScene().addUINode(specialPointBarView);
        getGameScene().addUINode(statAtackBarView);
        getGameScene().addUINode(statBarView);
        getGameScene().addUINode(nameCharaterText);
        getGameScene().addUINode(porcentAtackText);
    }

    public void pintarBordeIcono(String name,Button button){

        Entity entity = GameApp.currentEntity;
        for (Entity cantidadEntidades : GameApp.playersSelected){
            if (entity.getComponent(CombatStatsComponent.class).name.equals(name)){
                GameApp.setActionsOnClick(name);
                DropShadow dropShadow = new DropShadow();
                dropShadow.setBlurType(BlurType.ONE_PASS_BOX); // Tipo de desenfoque
                dropShadow.setColor(Color.GREEN);                // Color del borde
                dropShadow.setRadius(15);                     // Radio mínimo para nitidez
                dropShadow.setSpread(1.0);                     // Extensión completa para solidez
                dropShadow.setOffsetX(0);
                dropShadow.setOffsetY(0);
                button.setEffect(dropShadow);
            }else {
                GameApp.setActionsOnClick(name);
                DropShadow dropShadow = new DropShadow();
                dropShadow.setBlurType(BlurType.ONE_PASS_BOX); // Tipo de desenfoque
                dropShadow.setColor(Color.RED);                // Color del borde
                dropShadow.setRadius(15);                     // Radio mínimo para nitidez
                dropShadow.setSpread(1.0);                     // Extensión completa para solidez
                dropShadow.setOffsetX(0);
                dropShadow.setOffsetY(0);
                button.setEffect(dropShadow);
            }
        }



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
