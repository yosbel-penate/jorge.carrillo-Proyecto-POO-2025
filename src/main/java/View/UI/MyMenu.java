package View.UI;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MyMenu extends FXGLMenu {

    public MyMenu(MenuType type) {
        super(type);

        // Fondo
        Image fondo = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/fondo.png"
        )).toExternalForm());

        ImageView fondoView = new ImageView(fondo);
        fondoView.setFitWidth(getAppWidth());
        fondoView.setFitHeight(getAppHeight());
        fondoView.setPreserveRatio(false);

        getContentRoot().getChildren().add(fondoView);

        // Contenedor de botones
        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 100);
        menuBox.setTranslateY(getAppHeight() / 2.0 - 100);

        // Botón: Nuevo Juego
        Button btnNewGame = new Button("Nuevo Juego");
        btnNewGame.setOnAction(e -> getGameController().startNewGame());

        // Botón: Continuar
        Button btnContinue = new Button("Continuar Juego");
        btnContinue.setOnAction(e -> getGameController().gotoPlay());

        // Botón: Opciones
        Button btnOptions = new Button("Opciones");
        btnOptions.setOnAction(e -> fireOptions());

        // Botón: Salir
        Button btnExit = new Button("Salir al Escritorio");
        btnExit.setOnAction(e -> fireExit());

        // Aplicar estilo base
        for (Button btn : new Button[]{btnNewGame, btnContinue, btnOptions, btnExit}) {
            btn.setPrefWidth(200);
            btn.setStyle("-fx-font-size: 18px; -fx-background-color: rgba(0,0,0,0.6); -fx-text-fill: white;");
        }

        // Agrega todos los botones al contenedor
        menuBox.getChildren().addAll(btnNewGame, btnContinue, btnOptions, btnExit);
        getContentRoot().getChildren().add(menuBox);
    }
}
