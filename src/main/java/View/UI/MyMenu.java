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

    private final SubScene charactersSeleccion;

    public MyMenu(MenuType type) {
        super(type);

        // Fondo
        ImageView bg = createImageView("/assets/textures/fondo.png", getAppWidth(), getAppHeight());
        getContentRoot().getChildren().add(bg);

        // Escena de selección de personajes
        charactersSeleccion = new EscenaSeleccion();

        // Botón de inicio
        Button btnStart = createImageButton(
                "/assets/textures/ui/playButtonMainMenu.png",
                50, 400,
                () -> FXGL.getSceneService().pushSubScene(charactersSeleccion)
        );
        UI.animacionPresionarBoton(btnStart);
        getContentRoot().getChildren().add(btnStart);
    }

    private ImageView createImageView(String resourcePath, double width, double height) {
        Image img = new Image(Objects.requireNonNull(getClass().getResource(resourcePath)).toExternalForm());
        ImageView view = new ImageView(img);
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setPreserveRatio(false);
        return view;
    }

    private Button createImageButton(String resourcePath, double x, double y, Runnable onClick) {
        Image img = new Image(Objects.requireNonNull(getClass().getResource(resourcePath)).toExternalForm());
        ImageView icon = new ImageView(img);
        Button btn = new Button();
        btn.setGraphic(icon);
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        btn.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        btn.setOnAction(e -> onClick.run());
        return btn;
    }
}