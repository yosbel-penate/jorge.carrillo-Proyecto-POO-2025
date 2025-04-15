package View.UI;

import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static Domain.Settings.SettingsGame.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class UI {

    Image panelCombat;
    Image lifePointPlayer;
    Image lifePointEnemi;
    Image heartBar;
    Image heartBarEnemi;
    ImageView panelCombatView;
    ImageView heartBarImageView;
    ImageView heartBarImageViewEnemi;

    // Nuevos atributos para la barra de vida dinámica.
    private HBox heartsBarPlayer;
    private HBox heartsBarEnemi;
    private int maxHealthPlayer = 8;
    private int maxHealthEnemi = 5;
    private int currentHealthPlayer = 0;
    private int currentHealthEnemi = 5;



    public void showUI()
    {
        heartBar = getAssetLoader().loadImage("life_bar.png");
        heartBarEnemi = getAssetLoader().loadImage("barra_enemigo.png");
        lifePointPlayer = getAssetLoader().loadImage("life_point.png");
        lifePointEnemi = getAssetLoader().loadImage("life_point_enemi.png");


        //ImagesViews
        heartBarImageView = new ImageView();
        heartBarImageView.setImage(heartBar);
        heartBarImageViewEnemi = new ImageView();
        heartBarImageViewEnemi.setImage(heartBarEnemi);

        //Positions
        heartBarImageView.setTranslateX(TILE_SIZE);
        heartBarImageView.setTranslateY(TILE_SIZE * 15);

        heartBarImageViewEnemi.setTranslateX(TILE_SIZE * 21);
        heartBarImageViewEnemi.setTranslateY(TILE_SIZE * 15);


        //=========Barra de vida de players
        heartsBarPlayer = new HBox(5); // Espaciado de 5 píxeles entre corazones.
        for (int i = 0; i < maxHealthPlayer; i++) {
            ImageView heartView = new ImageView(lifePointPlayer);
            // Opcional: ajustar tamaño de cada imagen
            heartView.setFitWidth(32);
            heartView.setFitHeight(32);
            heartsBarPlayer.getChildren().add(heartView);
        }
        // Posicionar la barra de vida en la UI.
        heartsBarPlayer.setTranslateX(TILE_SIZE + 12);
        heartsBarPlayer.setTranslateY(TILE_SIZE * 15 + 13);


        //==========Barra de vida de enemies
        heartsBarEnemi = new HBox(5); // Espaciado de 5 píxeles entre corazones.
        heartsBarEnemi.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT); // Inviertes el orden

        for (int i = 0; i < maxHealthEnemi; i++) {
            ImageView heartViewEnemi = new ImageView(lifePointEnemi);
            // Opcional: ajustar tamaño de cada imagen
            heartViewEnemi.setFitWidth(32);
            heartViewEnemi.setFitHeight(32);
            heartsBarEnemi.getChildren().add(heartViewEnemi);
        }
        // Posicionar la barra de vida en la UI.
        heartsBarEnemi.setTranslateX(TILE_SIZE * 27 + 15);
        heartsBarEnemi.setTranslateY(TILE_SIZE * 15 + 12);


        //Add Nodes
        getGameScene().addUINode(heartBarImageView);
        getGameScene().addUINode(heartsBarPlayer);
        getGameScene().addUINode(heartBarImageViewEnemi);
        getGameScene().addUINode(heartsBarEnemi);
    }

    /**
     * Actualiza la visualización de la barra de vida del jugador.
     * Recorre los corazones de la barra y oculta aquellos que exceden la vida actual.
     */
    public void updateHealthBarPlayer() {
        for (int i = 0; i < maxHealthPlayer; i++) {
            ImageView heartView = (ImageView) heartsBarPlayer.getChildren().get(i);
            if (i < currentHealthPlayer) {
                heartView.setVisible(true);
                // Opcional: resetear la imagen, por si se modificó
                heartView.setImage(lifePointPlayer);
            } else {
                heartView.setVisible(false);
            }
        }
    }
    public void updateHealthBarEnemi() {
        for (int i = 0; i < maxHealthEnemi; i++) {
            ImageView heartView = (ImageView) heartsBarEnemi.getChildren().get(i);
            if (i < currentHealthEnemi) {
                heartView.setVisible(true);
                // Opcional: resetear la imagen, por si se modificó
                heartView.setImage(lifePointEnemi);
            } else {
                heartView.setVisible(false);
            }
        }
    }

    /**
     * Actualiza la vida actual del jugador, forzándola a estar entre 0 y el máximo.
     */
    public void setHealthEnemi(int health) {
        currentHealthEnemi = Math.max(0, Math.min(health, maxHealthEnemi));
        updateHealthBarEnemi();
    }
    public void setHealthPLayer(int health) {
        currentHealthPlayer = Math.max(0, Math.min(health, maxHealthPlayer));
        updateHealthBarEnemi();
    }

    /**
     * Reduce la vida actual del jugador en la cantidad especificada.
     */
    public void reduceHealthPlayer(int amount) {
        currentHealthPlayer -= amount;
        if (currentHealthPlayer < 0)
            currentHealthPlayer = 0;
        updateHealthBarPlayer();
    }
    public void reduceHealthEnemi(int amount) {
        currentHealthEnemi -= amount;
        if (currentHealthEnemi < 0)
            currentHealthEnemi = 0;
        updateHealthBarEnemi();
    }

}
