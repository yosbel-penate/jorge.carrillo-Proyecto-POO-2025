package App.Services;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

import App.Components.CombatStatsComponent;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.List;

import View.UI.CombatModeUI;
import View.UI.UI;


public class AcertijoService {

 private CombatModeUI ui;
 private boolean barrierOpen = false;

    // Constructor que recibe la UI y la almacena
    public AcertijoService(CombatModeUI ui) {
        this.ui = ui;
    }

    private final List<Acertijo> acertijos = List.of(
        new Acertijo("acertijo1.png", "B"),
        new Acertijo("acertijo2.png", "C"),
        new Acertijo("acertijo3.png", "B"),
        new Acertijo("acertijo4.png", "B"),
        new Acertijo("acertijo5.png", "C"),
        new Acertijo("acertijo6.png", "A")
    );

    private int acertijoActual = 0;

    private final double[][][] coordenadas = {
        { {260, 920}, {256, 1220}, {264, 1524}, {716, 1784} },
        { {144, 693}, {159, 924}, {165, 1131}, {531, 1329} },
        { {268, 936}, {272, 1200}, {264, 1468}, {708, 1756} },
        { {171, 711}, {153, 921}, {159, 1131}, {525, 1329} },
        { {153, 705}, {147, 915}, {159, 1137}, {510, 1332} },
        { {252, 1008}, {244, 1292}, {248, 1576}, {696, 1836} }
    };

    private final int[] imagenesAnchoOriginal = {2048, 1536, 2048, 1536, 1536, 2048};
    private final int[] imagenesAltoOriginal = {2048, 1536, 2048, 1536, 1536, 2048};

    public void mostrarSiguienteAcertijo(Entity barrier, Entity player, CombatModeUI combatModeUI) {
       if (barrierOpen) {
            return;
        }
   
        if (acertijoActual >= acertijos.size()) {
        return;
    }

    Acertijo actual = acertijos.get(acertijoActual);
    double[][] coordsOriginal = coordenadas[acertijoActual];

    Image image = new Image("assets/textures/" + actual.getImagen());
    ImageView img = new ImageView(image);
    img.setPreserveRatio(true);

    double fitWidth = 600;
    img.setFitWidth(fitWidth);

    double scaleX = fitWidth / imagenesAnchoOriginal[acertijoActual];
    double scale = scaleX;

    Pane layout = new Pane();
    img.setLayoutX(0);
    img.setLayoutY(0);
    layout.getChildren().add(img);

    List<String> opciones = List.of("A", "B", "C");
    for (int i = 0; i < opciones.size(); i++) {
        String opcionTexto = opciones.get(i); // copia única para cada botón
        javafx.scene.control.Button btn = new javafx.scene.control.Button();

        btn.setPrefSize(1550 * scale, 169 * scale);
        btn.setLayoutX(coordsOriginal[i][0] * scale);
        btn.setLayoutY(coordsOriginal[i][1] * scale);
        btn.setStyle("-fx-background-color: transparent;");

        btn.setOnAction(e -> {
    verificarRespuesta(opcionTexto, actual.getRespuestaCorrecta(), barrier, layout, player);
});


        layout.getChildren().add(btn);
    }

    double comprarAncho = 649;
    double comprarAlto = 169;

    javafx.scene.control.Button comprar = new javafx.scene.control.Button();
    comprar.setPrefSize(comprarAncho * scale, comprarAlto * scale);
    comprar.setLayoutX(coordsOriginal[3][0] * scale);
    comprar.setLayoutY(coordsOriginal[3][1] * scale);
    comprar.setStyle("-fx-background-color: transparent;");

    comprar.setOnAction(e -> {
        int monedas = FXGL.geti("coins");


        Text texto = new Text();
        texto.setFill(monedas >= 5 ? Color.GOLD : Color.RED);
        texto.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
        texto.setTranslateX(150);
        texto.setTranslateY(50 );

        if (UI.cantidadMoneda >= 5) {
            UI.updateAmountCoins(-5); // Resta 5 monedas usando tu sistema
            texto.setText("Respuesta: " + actual.getRespuestaCorrecta());
            MusicService.playCoin();
        } else {
            texto.setText("¡Insuficientes monedas!");
        }

        layout.getChildren().add(texto);
        FXGL.getGameTimer().runOnceAfter(() -> layout.getChildren().remove(texto), Duration.seconds(1.5));
    });

    layout.getChildren().add(comprar);

    FXGL.getGameScene().addUINode(layout);
}

   private void verificarRespuesta(String seleccion, String correcta, Entity barrier, Pane layout, Entity player)
 {
    if (seleccion.equals(correcta)) {
        Point2D itemPosition = barrier.getPosition();
        barrier.removeFromWorld();
        FXGL.spawn("barrierDisabled", itemPosition);
        FXGL.getGameScene().removeUINode(layout);
    } else {
        CombatStatsComponent stats = player.getComponent(CombatStatsComponent.class);
        stats.currentHealth = Math.max(0, stats.currentHealth - 5); // evita negativos
        ui.updateHealthBarPlayer(player);


        Text texto = new Text("- 5");
        texto.setFill(Color.RED);
        texto.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
        // Ajusta la posición del texto 
        texto.setTranslateX(150 );
        texto.setTranslateY(50);

        FXGL.getGameScene().addUINode(texto);
        FXGL.getGameTimer().runOnceAfter(() -> FXGL.getGameScene().removeUINode(texto), Duration.seconds(1));
    }

    acertijoActual++;
}

}


