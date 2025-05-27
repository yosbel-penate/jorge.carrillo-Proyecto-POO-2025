package View.UI;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


public class MyMenu extends FXGLMenu {

    private SubScene charactersSeleccion;

    public MyMenu(MenuType type) {
        super(type);


        // 1. Carga la textura desde assets/textures
        Image tex = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/fondo.png"
        )).toExternalForm());

        // 2. Obtén el ImageView y ajústalo
        ImageView bg = new ImageView();
        bg.setImage(tex);
        bg.setFitWidth(getAppWidth());
        bg.setFitHeight(getAppHeight());
        bg.setPreserveRatio(false);

        // 3. Añádelo al content root (al fondo)
        getContentRoot().getChildren().add(bg);

        // 2. Vista del botón
        Image boton = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/ui/playButtonMainMenu.png"
        )).toExternalForm());

        ImageView icon = new ImageView();
        icon.setImage(boton);
        Button btnStart = new Button();

        btnStart.setGraphic(icon);
        btnStart.setTranslateX(50);
        btnStart.setTranslateY(400);
        btnStart.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        charactersSeleccion = new EscenaSeleccion();

        btnStart.setOnAction(e -> {

            FXGL.getSceneService().pushSubScene(charactersSeleccion);

        });

        UI.animacionPresionarBoton(btnStart);

        getContentRoot().getChildren().add(btnStart);
    }
}