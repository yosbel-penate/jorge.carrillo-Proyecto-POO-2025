package View.UI;

import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import App.Services.MusicService;
import Domain.Entity.Characters.Players.Cyborg;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.ViewComponent;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
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

    public static int cantidadMoneda = 0;

    public static int getCantidadMoneda() {
        return cantidadMoneda;
    }

    // Images
    private Button jaxKaneBtn, marcusBtn, cyborgBtn, zaraBtn, toxicBtn;
    private Image wikiImage, buttonWikiImage, buttonGameMenuImage, buttonMainMenuImage;
    private Image statBar, statAtackBar, specialPointsBarImage, imageTienda, latienda, iconCoin;

    // Text
    private Text nameCharaterText, porcentAtackText;

    public static boolean botonStatus;
    public static boolean bordeAplicado = false;

    // ImageViews
    private ImageView iconCoinView, buttonWikiView, wikiView, buttonGameMenuView, buttonMainMenuView;
    private ImageView specialPointBarView, statAtackBarView, statBarView, viewLatienda;

    private static HBox barraIdentificadores;

    // Buttons
    private Button buttonWiki, buttonTienda, gameMenuButton, mainMenuButton;
    private static ArrayList<Button> listaBotones;

    private static Text contadorMonedas;

    // Campo de clase:
    private static final Map<String, Button> mapNombreAButton = new HashMap<>();

    // Utilidad para crear botones de imagen
    private Button createImageButton(Image image, double x, double y, double width, double height, Runnable onClick) {
        ImageView view = new ImageView(image);
        Button btn = new Button();
        btn.setPrefSize(width, height);
        btn.setGraphic(view);
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        btn.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        if (onClick != null) btn.setOnAction(e -> onClick.run());
        animacionPresionarBoton(btn);
        return btn;
    }

    // Utilidad para crear ImageView
    private ImageView createImageView(Image image, double x, double y, Double width, Double height) {
        ImageView view = new ImageView(image);
        view.setX(x);
        view.setY(y);
        if (width != null) view.setFitWidth(width);
        if (height != null) view.setFitHeight(height);
        return view;
    }

    public void splashAnimacion(ImageView splash) {
        FXGL.getGameTimer().runOnceAfter(() -> {
            FadeTransition ft = new FadeTransition(Duration.seconds(1), splash);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setOnFinished(evt -> FXGL.getGameScene().removeUINode(splash));
            ft.play();
        }, Duration.seconds(2.0));
    }

    public static void quitarBordes() {
        for (Entity entity : GameApp.playersSelected) {
            Node viewNode = entity.getViewComponent().getChildren().get(0);
            viewNode.setEffect(null);
        }
    }

    public static void borderEntityIdentifier() {
        for (Entity entity : GameApp.playersSelected) {
            ViewComponent viewComponent = entity.getViewComponent();
            Node viewNode = viewComponent.getChildren().get(0);
            DropShadow dropShadow = new DropShadow();
            dropShadow.setBlurType(BlurType.ONE_PASS_BOX);
            if (entity != GameApp.currentEntity) {
                dropShadow.setColor(Color.RED);
                dropShadow.setRadius(9);
            } else {
                dropShadow.setColor(Color.BLUE);
                dropShadow.setRadius(5);
            }
            dropShadow.setSpread(1.0);
            dropShadow.setOffsetX(0);
            dropShadow.setOffsetY(0);
            viewNode.setEffect(dropShadow);
        }
    }

    public void showUI() {
        // Splash
        ImageView splashLevel1 = createImageView(getAssetLoader().loadImage("nivel1Texto.png"), TILE_SIZE * 10, TILE_SIZE * 2, null, null);
        splashAnimacion(splashLevel1);
        getGameScene().addUINode(splashLevel1);

        // Botón resaltar
        Button botonResaltar = createImageButton(
                getAssetLoader().loadImage("imageBotonResaltar.png"),
                TILE_SIZE, TILE_SIZE * 6 + 30, 70, 70,
                () -> {
                    MusicService.playKey();
                    botonStatus = !botonStatus;
                    if (botonStatus && !bordeAplicado) {
                        borderEntityIdentifier();
                        bordeAplicado = true;
                    } else if (!botonStatus && bordeAplicado) {
                        quitarBordes();
                        bordeAplicado = false;
                    }
                }
        );

        // Monedas
        iconCoin = getAssetLoader().loadImage("targetCoin.png");
        iconCoinView = createImageView(iconCoin, TILE_SIZE * 27 + 20, TILE_SIZE * 17 + 20, null, null);

        contadorMonedas = new Text("x 0");
        contadorMonedas.setFill(Color.YELLOW);
        contadorMonedas.setFont(Font.font("Negrita", 20));
        contadorMonedas.setX(TILE_SIZE * 28);
        contadorMonedas.setY(TILE_SIZE * 18 - 10);

        // Tienda
        latienda = getAssetLoader().loadImage("latienda.png");
        viewLatienda = createImageView(latienda, TILE_SIZE * 10, TILE_SIZE * 5, null, null);
        imageTienda = getAssetLoader().loadImage("botonTienda.png");
        buttonTienda = createImageButton(imageTienda, TILE_SIZE, TILE_SIZE * 5 + 10, 70, 70, this::setImageTienda);

        // Textos de stats
        porcentAtackText = new Text();
        porcentAtackText.setTranslateX(TILE_SIZE * 8);
        porcentAtackText.setTranslateY(TILE_SIZE * 16 + 23);
        porcentAtackText.setFill(Color.CYAN);
        porcentAtackText.setFont(Font.font("Negrita", 20));

        nameCharaterText = new Text();
        nameCharaterText.setTranslateX(TILE_SIZE * 4);
        nameCharaterText.setTranslateY(TILE_SIZE * 16 + 27);
        nameCharaterText.setFill(Color.CYAN);
        nameCharaterText.setFont(Font.font("Negrita", 25));

        updateCurrentPlayerStats(GameApp.currentEntity);

        // Carga de imágenes
        buttonGameMenuImage = getAssetLoader().loadImage("botonGameMenu.png");
        buttonMainMenuImage = getAssetLoader().loadImage("buttonMainMenu.png");
        buttonWikiImage = getAssetLoader().loadImage("wikiButton.png");
        specialPointsBarImage = getAssetLoader().loadImage("specialPointBar.png");
        statBar = getAssetLoader().loadImage("statBar.png");
        statAtackBar = getAssetLoader().loadImage("statAtackBar.png");

        // Wiki
        buttonWiki = createImageButton(buttonWikiImage, TILE_SIZE, TILE_SIZE * 4 - 10, 70, 70, this::setImageWiki);
        wikiImage = getAssetLoader().loadImage("wiki_image.png");
        wikiView = new ImageView(wikiImage);
        wikiView.setTranslateX(TILE_SIZE * 8);
        wikiView.setTranslateY(TILE_SIZE * 5);

        // Special Point Bar
        specialPointBarView = createImageView(specialPointsBarImage, TILE_SIZE * 3 + 5, TILE_SIZE * 15 - 3, 245.0, 50.0);

        // Stat Bar
        statBarView = createImageView(statBar, TILE_SIZE, TILE_SIZE * 15 - 10, null, null);
        statAtackBarView = createImageView(statAtackBar, TILE_SIZE * 7, TILE_SIZE * 16, null, null);

        // Botones de menú
        gameMenuButton = createImageButton(buttonGameMenuImage, TILE_SIZE, TILE_SIZE, 70, 70, () -> {
            FXGL.getGameController().gotoGameMenu();
            MusicService.playKey();
            animacionPresionarBoton(gameMenuButton);
        });

        mainMenuButton = createImageButton(buttonMainMenuImage, TILE_SIZE, TILE_SIZE * 3 - 30, 70, 70, () -> {
            FXGL.getGameController().gotoMainMenu();
            MusicService.playKey();
            animacionPresionarBoton(mainMenuButton);
        });

        // HBox de identificadores de players
        barraIdentificadores = new HBox(10);
        barraIdentificadores.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        barraIdentificadores.setTranslateX(TILE_SIZE * 22);
        barraIdentificadores.setTranslateY(TILE_SIZE);

        listaBotones = new ArrayList<>();
        addPlayerButtonIfSelected("cyborg", EscenaSeleccion.cyborgBool, "iconCyborg.png");
        addPlayerButtonIfSelected("jaxKane", EscenaSeleccion.jaxKaneBool, "iconJaxKane.png");
        addPlayerButtonIfSelected("zaraQuinn", EscenaSeleccion.zaraQuinnBool, "iconZaraQuinn.png");
        addPlayerButtonIfSelected("toxico", EscenaSeleccion.toxicBool, "iconToxic.png");
        addPlayerButtonIfSelected("EngineerMarcus", EscenaSeleccion.marcusBool, "iconEngineerMarcus.png");

        // Añadir nodos a la escena
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
        getGameScene().addUINode(botonResaltar);
        getGameScene().addUINode(iconCoinView);
        getGameScene().addUINode(contadorMonedas);
    }

    private void addPlayerButtonIfSelected(String name, boolean isSelected, String iconFile) {
        if (!isSelected) return;
        Image icon = getAssetLoader().loadTexture(iconFile).getImage();
        Button btn = new Button();
        animacionPresionarBoton(btn);
        btn.setGraphic(new ImageView(icon));
        btn.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        btn.setOnAction(e -> pintarBordeIcono(name, btn));
        listaBotones.add(btn);
        barraIdentificadores.getChildren().add(btn);
        mapNombreAButton.put(name, btn);
    }

    public static void seleccionarResaltar() {
        if (botonStatus) {
            borderEntityIdentifier();
        }
    }

    public void updateCurrentPlayerStats(Entity currentEntity) {
        CombatStatsComponent stats = currentEntity.getComponent(CombatStatsComponent.class);
        nameCharaterText.setText(stats.name);
        porcentAtackText.setText("Attack + " + stats.getAtacck());
    }

    public void pintarBordeIcono(String nameSeleccionado, Button botonClicado) {
        GameApp.setActionsOnClick(nameSeleccionado);
        for (Map.Entry<String, Button> entry : mapNombreAButton.entrySet()) {
            String nombre = entry.getKey();
            Button btn = entry.getValue();
            DropShadow sombra = new DropShadow(BlurType.ONE_PASS_BOX,
                    nombre.equals(nameSeleccionado) ? Color.GREEN : Color.RED,
                    15, 1.0, 0, 0);
            btn.setEffect(sombra);
        }
    }

    private void setImageTienda() {
        if (getGameScene().getUINodes().contains(viewLatienda)) {
            getGameScene().removeUINode(viewLatienda);
        } else {
            getGameScene().addUINode(viewLatienda);
        }
        wikiView.addEventFilter(MouseEvent.MOUSE_PRESSED, MouseEvent::consume);
        Scene scene = getGameScene().getRoot().getScene();
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (!getGameScene().getUINodes().contains(viewLatienda)) return;
            double x = e.getX();
            double y = e.getY();
            if (x > 915 && x < 980 && y > 260 && y < 310) getGameScene().removeUINode(viewLatienda);
            if (!(x > 850 && x < 975)) return;
            if (y > 405 && y < 460) {
                System.out.println("Ha comprado el item 1");
            } else if (y > 505 && y < 560) {
                System.out.println("Ha comprado el item 2");
            } else if (y > 605 && y < 660) {
                System.out.println("Ha comprado el item 3");
            }
        });
    }

    private void setImageWiki() {
        getGameScene().addUINode(wikiView);
        wikiView.addEventFilter(MouseEvent.MOUSE_PRESSED, MouseEvent::consume);
        Scene scene = getGameScene().getRoot().getScene();
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (getGameScene().getUINodes().contains(wikiView)) {
                getGameScene().removeUINode(wikiView);
            }
        });
    }

    public void atackBar(int amount, Entity player) {
        int total = player.getComponent(CombatStatsComponent.class).atacck += amount;
        porcentAtackText.setText("Atacck +" + total);
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
            pressAnim.stop();
            pressAnim.playFromStart();
        });

        btn.setOnMouseReleased(e -> {
            releaseAnim.stop();
            releaseAnim.playFromStart();
        });
    }

    public static void updateAmountCoins(int cantidad) {
        if (cantidadMoneda >= 0) {
            int total = cantidadMoneda += cantidad;
            contadorMonedas.setText("X " + total);
        }
    }
}