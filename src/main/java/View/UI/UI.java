package View.UI;

import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import App.Services.MusicService;
import Domain.Entity.Characters.Players.Cyborg;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.animation.ScaleTransition;
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
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class UI {

   static int cantidadMoneda = 0;
    //Images
    Image wikiImage;
    Image buttonWikiImage;
    Image buttonGameMenuImage;
    Image buttonMainMenuImage;
    Image statBar;
    Image statAtackBar;
    Image specialPointsBarImage;
    Image imageTienda;
    Image latienda;
    Image iconCoin;

    //Text
    Text nameCharaterText;
    Text porcentAtackText;

    //ImagesView
    ImageView iconCoinView;
    ImageView buttonWikiView;
    ImageView wikiView;
    ImageView buttonGameMenuView;
    ImageView buttonMainMenuView;
    ImageView specialPointBarView;
    ImageView statAtackBarView;
    ImageView statBarView;
    ImageView viewLatienda;

    //Buttons
    Button buttonWiki;
    Button buttonTienda;
    Button gameMenuButton;
    Button mainMenuButton;


    static Text contadorMonedas;


    // Campo de clase:
    private Map<String, Button> mapNombreAButton = new HashMap<>();


    public void showUI()
    {

        Image coinConteiner = getAssetLoader().loadImage("coinConteiner.png");
        ImageView coinConteinerView = new ImageView(coinConteiner);
        coinConteinerView.setX(TILE_SIZE * 27);
        coinConteinerView.setY(TILE_SIZE * 17);

        getGameScene().addUINode(coinConteinerView);
        //Logica de monedas
        iconCoin = getAssetLoader().loadImage("targetCoin.png");
        iconCoinView = new ImageView();
        iconCoinView.setImage(iconCoin);
        iconCoinView.setX(TILE_SIZE  * 27 + 20);
        iconCoinView.setY(TILE_SIZE * 17 + 20);


        contadorMonedas = new Text();
        contadorMonedas.setText("x " + 0);
        contadorMonedas.setFill(Color.YELLOW);
        contadorMonedas.setFont(Font.font("Negrita",20));
        contadorMonedas.setX(TILE_SIZE * 28);
        contadorMonedas.setY(TILE_SIZE * 18 - 10);

        latienda = getAssetLoader().loadImage("latienda.png");
        viewLatienda = new ImageView();
        viewLatienda.setImage(latienda);
        viewLatienda.setX(TILE_SIZE * 10);
        viewLatienda.setY(TILE_SIZE * 5);
        //Boton Tienda
        imageTienda = getAssetLoader().loadImage("botonTienda.png");
        buttonTienda = new Button();
        buttonTienda.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        buttonTienda.setGraphic(new ImageView(imageTienda));
        buttonTienda.setTranslateX(TILE_SIZE);
        buttonTienda.setTranslateY(TILE_SIZE * 5 + 10);
        buttonTienda.setOnAction(e -> {
            setImageTienda();
            MusicService.playKey();
        });

        porcentAtackText = new Text();
        porcentAtackText.setTranslateX(TILE_SIZE * 8);
        porcentAtackText.setTranslateY(TILE_SIZE * 16 + 23);
        porcentAtackText.setFill(Color.CYAN);
        porcentAtackText.setFont(Font.font("Negrita",20));

        nameCharaterText = new Text();
        nameCharaterText.setTranslateX(TILE_SIZE * 4);
        nameCharaterText.setTranslateY(TILE_SIZE * 16 + 27);
        nameCharaterText.setFill(Color.CYAN);
        nameCharaterText.setFont(Font.font("Negrita", 25));


       updateCurrentPlayerStats(GameApp.currentEntity);

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
            animacionPresionarBoton(gameMenuButton);
        });
        buttonWiki.setOnAction(e -> {
            setImageWiki();
            MusicService.playKey();
            animacionPresionarBoton(buttonWiki);
        });
        mainMenuButton.setOnAction(e -> {
            FXGL.getGameController().gotoMainMenu();
            MusicService.playKey();
            animacionPresionarBoton(mainMenuButton);

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


        mapNombreAButton.put("cyborg",  cyborgBtn);
        mapNombreAButton.put("jaxKane",  jaxKaneBtn);

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
        getGameScene().addUINode(buttonTienda);
        getGameScene().addUINode(iconCoinView);
        getGameScene().addUINode(contadorMonedas);

    }


    public void updateCurrentPlayerStats(Entity currentEntity){
        //Caja de informacion
        System.out.println("se llama al metodo");
        System.out.println(currentEntity.getComponent(CombatStatsComponent.class).name);
        CombatStatsComponent stats =
                currentEntity.getComponent(CombatStatsComponent.class);

        // Actualiza el texto, no crees un Text nuevo
        nameCharaterText.setText(stats.name);
        porcentAtackText.setText("Attack + " + stats.getAtacck());
    }

    public void pintarBordeIcono(String nameSeleccionado, Button botonClicado) {
        // 1) Primero cambiamos la entidad activa
        GameApp.setActionsOnClick(nameSeleccionado);

        // 2) Ahora recorremos cada jugador y su botón asociado
        for (Map.Entry<String, Button> entry : mapNombreAButton.entrySet()) {
            String nombre = entry.getKey();
            Button btn   = entry.getValue();

            // Si el nombre coincide con el seleccionado, pongo verde; si no, rojo
            DropShadow sombra = new DropShadow(BlurType.ONE_PASS_BOX,
                    nombre.equals(nameSeleccionado) ? Color.GREEN : Color.RED,
                    15, 1.0, 0, 0);
            btn.setEffect(sombra);
        }
    }

    private void setImageTienda(){
        getGameScene().addUINode(viewLatienda);

        wikiView.addEventFilter(MouseEvent.MOUSE_PRESSED, MouseEvent::consume);

        Scene scene = getGameScene().getRoot().getScene();
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            // Si la wikiView sigue en la UI, la quitamos
            if (getGameScene().getUINodes().contains(viewLatienda)) {
                getGameScene().removeUINode(viewLatienda);
            }
        });
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

    public static void animacionPresionarBoton(Button btn) {


        ScaleTransition pressAnim = new ScaleTransition(Duration.millis(100), btn);
        pressAnim.setFromX(1.0);
        pressAnim.setFromY(1.0);
        pressAnim.setToX(0.95);
        pressAnim.setToY(0.95);

        ScaleTransition releaseAnim = new ScaleTransition(Duration.millis(100), btn);
        releaseAnim.setFromX(0.95);
        releaseAnim.setFromY(0.95);
        releaseAnim.setToX(1.0);
        releaseAnim.setToY(1.0);

        btn.setOnMousePressed(e -> {
            // Reiniciamos y reproducimos
            pressAnim.stop();
            pressAnim.playFromStart();
        });

        btn.setOnMouseReleased(e -> {
            releaseAnim.stop();
            releaseAnim.playFromStart();
        });
    }


    public static void updateAmountCoins (int cantidad){
        if (cantidadMoneda >= 0){
            int total = cantidadMoneda += cantidad;
            contadorMonedas.setText("X " + total);
        }

    }
}
