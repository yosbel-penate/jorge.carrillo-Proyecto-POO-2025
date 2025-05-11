package View.UI;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.fasterxml.jackson.databind.util.BeanUtil;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class EscenaSeleccion extends SubScene {
    public EscenaSeleccion() {
        super();

        // --- Fondo ---
        Image bgImg = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/fondoSeleccion.jpg"
        )).toExternalForm());
        ImageView bgView = new ImageView(bgImg);
        bgView.setFitWidth(FXGL.getAppWidth());
        bgView.setFitHeight(FXGL.getAppHeight());
        bgView.setPreserveRatio(false);

        //Boton del personaje(Cyborg)
        Image personaje = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaCyborg.jpg"
        )).toExternalForm());
        ImageView personajeView = new ImageView();
        personajeView.setImage(personaje);

        Button personajeBUtton = new Button();
        personajeBUtton.setGraphic(personajeView);
        personajeBUtton.setTranslateX(300);
        personajeBUtton.setTranslateY(40);
        personajeBUtton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        //Boton del personaje(Cyborg)
        Image agent = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaAgent.jpg"
        )).toExternalForm());
        ImageView agentView = new ImageView();
        personajeView.setImage(personaje);

        Button agentBUtton = new Button();
        personajeBUtton.setGraphic(agentView);
        personajeBUtton.setTranslateX(300);
        personajeBUtton.setTranslateY(40);
        personajeBUtton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        // Ponemos el bgView *detrás* de todo
        getContentRoot().getChildren().add(bgView);
        getContentRoot().getChildren().add(personajeBUtton);

        // --- Contenido encima del fondo ---
        VBox content = new VBox(20);
        content.setTranslateX(200);
        content.setTranslateY(100);

    }
}
