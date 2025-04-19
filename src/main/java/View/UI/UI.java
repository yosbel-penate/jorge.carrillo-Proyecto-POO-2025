package View.UI;

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
    Image pasosPoint;
    Image heartBar;
    Image heartBarEnemi;
    Image barraPasos;
    Image barraPasosEnemigo;
    Image puntosPasosEnemigo;

    //ImagesView
    ImageView barraPasosView;
    ImageView heartBarImageView;
    ImageView heartBarImageViewEnemi;
    ImageView barraPasosEnemigoView;

    //Hbox
    private HBox heartsBarPlayer;
    private HBox heartsBarEnemi;
    private HBox barraPasosBOX;
    private HBox enemyStepsBox;

    //Instancia de LogicOfCombat
    LogicOfCombat combatStats = new LogicOfCombat();

    public void showUI()
    {
        //=============ImagesLoader=============
        heartBar = getAssetLoader().loadImage("life_bar.png");
        heartBarEnemi = getAssetLoader().loadImage("barra_enemigo.png");
        lifePointPlayer = getAssetLoader().loadImage("life_point.png");
        lifePointEnemi = getAssetLoader().loadImage("life_point_enemi.png");
        barraPasos = getAssetLoader().loadImage("barra_pasos.png");
        pasosPoint = getAssetLoader().loadImage("pasos_point.png");
        barraPasosEnemigo = getAssetLoader().loadImage("barra_pasos_ememigo.png");
        puntosPasosEnemigo = getAssetLoader().loadImage("pasos_point_enemi.png");


        //=============ImagesViews=============
        //Barra de vida del Jugador
        heartBarImageView = new ImageView();
        heartBarImageView.setImage(heartBar);
        heartBarImageView.setTranslateX(TILE_SIZE);
        heartBarImageView.setTranslateY(TILE_SIZE * 15);


        //Barra de vida del Enemigo
        heartBarImageViewEnemi = new ImageView();
        heartBarImageViewEnemi.setImage(heartBarEnemi);
        heartBarImageViewEnemi.setTranslateX(TILE_SIZE * 21);
        heartBarImageViewEnemi.setTranslateY(TILE_SIZE * 15);

        //Contador de pasos del Jugador
        barraPasosView = new ImageView();
        barraPasosView.setImage(barraPasos);
        barraPasosView.setTranslateX(TILE_SIZE);
        barraPasosView.setTranslateY(TILE_SIZE);

        //Contador de pasos del Enemigo
        barraPasosEnemigoView = new ImageView();
        barraPasosEnemigoView.setImage(barraPasosEnemigo);
        barraPasosEnemigoView.setTranslateX(TILE_SIZE * 22);
        barraPasosEnemigoView.setTranslateY(TILE_SIZE);


        //=========Barra de vida de players
        heartsBarPlayer = new HBox(5); // Espaciado de 5 píxeles entre corazones.
        for (int i = 0; i < combatStats.maxHealthPlayer; i++) {
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

        for (int i = 0; i < combatStats.maxHealthEnemi; i++) {
            ImageView heartViewEnemi = new ImageView(lifePointEnemi);
            // Opcional: ajustar tamaño de cada imagen
            heartViewEnemi.setFitWidth(32);
            heartViewEnemi.setFitHeight(32);
            heartsBarEnemi.getChildren().add(heartViewEnemi);
        }
        // Posicionar la barra de vida en la UI.
        heartsBarEnemi.setTranslateX(TILE_SIZE * 27 + 15);
        heartsBarEnemi.setTranslateY(TILE_SIZE * 15 + 12);


        //===============Barra de Pasos Players
        barraPasosBOX = new HBox(5); // Espaciado de 5 píxeles entre corazones.
        for (int i = 0; i < combatStats.maximoPasos; i++) {
            ImageView pasosPointView = new ImageView(pasosPoint);
            // Opcional: ajustar tamaño de cada imagen
            pasosPointView.setFitWidth(32);
            pasosPointView.setFitHeight(32);
            barraPasosBOX.getChildren().add(pasosPointView);
        }
        barraPasosBOX.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        // Posicionar la barra de vida en la UI.
        barraPasosBOX.setTranslateX(TILE_SIZE + 14);
        barraPasosBOX.setTranslateY(TILE_SIZE + 11);

        //Barra de pasos del Enemigo
        enemyStepsBox = new HBox(5); // Espaciado de 5 píxeles entre íconos.
        for (int i = 0; i < combatStats.enemyMaxSteps; i++) {
            ImageView enemyStepIcon = new ImageView(puntosPasosEnemigo);
            // Ajusta el tamaño si lo requieres
            enemyStepIcon.setFitWidth(32);
            enemyStepIcon.setFitHeight(32);
            enemyStepsBox.getChildren().add(enemyStepIcon);
        }
        // Posicionar la barra de pasos del enemigo; ajusta la posición según tu diseño.
        enemyStepsBox.setTranslateX(TILE_SIZE * 27);
        enemyStepsBox.setTranslateY(TILE_SIZE + 10);

        // Agrega el contenedor al UI


        //Add Nodes
        getGameScene().addUINode(heartBarImageView);
        getGameScene().addUINode(heartsBarPlayer);
        getGameScene().addUINode(heartBarImageViewEnemi);
        getGameScene().addUINode(heartsBarEnemi);
        getGameScene().addUINode(barraPasosView);
        getGameScene().addUINode(barraPasosBOX);
        getGameScene().addUINode(barraPasosEnemigoView);
        getGameScene().addUINode(enemyStepsBox);
    }

    /**
     * Actualiza la visualización de la barra de vida del jugador.
     * Recorre los corazones de la barra y oculta aquellos que exceden la vida actual.
     */
    public void updateHealthBarPlayer() {
        for (int i = 0; i < combatStats.maxHealthPlayer; i++) {
            ImageView heartView = (ImageView) heartsBarPlayer.getChildren().get(i);
            if (i < combatStats.currentHealthPlayer) {
                heartView.setVisible(true);
                // Opcional: resetear la imagen, por si se modificó
                heartView.setImage(lifePointPlayer);
            } else {
                heartView.setVisible(false);
            }
        }
    }

    public void updateHealthBarEnemi() {
        for (int i = 0; i < combatStats.maxHealthEnemi; i++) {
            ImageView heartView = (ImageView) heartsBarEnemi.getChildren().get(i);
            if (i < combatStats.currentHealthEnemi) {
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
        combatStats.currentHealthEnemi = Math.max(0, Math.min(health, combatStats.maxHealthEnemi));
        updateHealthBarEnemi();
    }
    public void setHealthPLayer(int health) {
        combatStats.currentHealthPlayer = Math.max(0, Math.min(health, combatStats.maxHealthPlayer));
        updateHealthBarEnemi();
    }

    /**
     * Reduce la vida actual del jugador en la cantidad especificada.
     */
    public void reduceHealthPlayer(int amount) {
        combatStats.currentHealthPlayer -= amount;
        if (combatStats.currentHealthPlayer < 0)
            combatStats.currentHealthPlayer = 0;
        updateHealthBarPlayer();
    }
    public void reduceHealthEnemi(int amount) {
        combatStats.currentHealthEnemi -= amount;
        if (combatStats.currentHealthEnemi < 0)
            combatStats.currentHealthEnemi = 0;
        updateHealthBarEnemi();
    }
    public void reduceStepPoint() {
        if (combatStats.numeroActualPasos < combatStats.maximoPasos) {
            // Se obtiene el ImageView correspondiente al paso a descontar
            ImageView pasoIcon = (ImageView) barraPasosBOX.getChildren().get(combatStats.numeroActualPasos);
            // Se puede optar por ocultarlo o cambiarle la opacidad para dar efecto de "gastado"
            pasoIcon.setVisible(false);
            pasoIcon.setOpacity(0.3);
            // Alternativamente, si no deseas ocultar por completo puedes reducir la opacidad:
            // pasoIcon.setOpacity(0.3);
            combatStats.numeroActualPasos++;
        }
    }
    /**
     * Reduce un punto de la barra de pasos del enemigo.
     */
    public void reduceEnemyStepPoint() {
        if (combatStats.enemyConsumedSteps < combatStats.enemyMaxSteps) {
            // Obtén el ícono correspondiente a ese paso y cambia su visibilidad o su opacidad
            ImageView enemyStepIcon = (ImageView) enemyStepsBox.getChildren().get(combatStats.enemyConsumedSteps);
            enemyStepIcon.setVisible(false); // O, alternativamente: enemyStepIcon.setOpacity(0.3);
            combatStats.enemyConsumedSteps++;
        }
    }
}
