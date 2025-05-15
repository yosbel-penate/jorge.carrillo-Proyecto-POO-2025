Aquí tienes una versión rediseñada del menú GameMenu utilizando la librería FXGL, con botones para las opciones que mencionaste:

package View.UI;

import App.Services.MusicService;
import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ViewComponent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameMenu extends FXGLDefaultMenu {
    public GameMenu() {
        super(MenuType.GAME_MENU);

        Pane root = getContentRoot();

        // Reproduce la música del menú
        MusicService.playMainMenu();

        // Limpia elementos predeterminados
        root.getChildren().removeIf(node -> node instanceof Rectangle || node instanceof StackPane);

        // Agrega fondo personalizado
        ImageView fondoView = FXGL.texture("fondoGameMenu.png", getAppWidth(), getAppHeight());
        fondoView.setPreserveRatio(false);
        fondoView.setFitWidth(getAppWidth());
        fondoView.setFitHeight(getAppHeight());
        root.getChildren().add(fondoView);

        // Contenedor de los botones
        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setTranslateX(getAppWidth() / 2.0 - 100);
        menuBox.setTranslateY(getAppHeight() / 2.0 - 150);

        // Botones
        Button btnResume = new Button("Reanudar juego");
        btnResume.setOnAction(e -> fireResume());

        Button btnRestart = new Button("Reiniciar nivel");
        btnRestart.setOnAction(e -> fireNewGame());

        Button btnOptions = new Button("Opciones");
        btnOptions.setOnAction(e -> fireOptions());

        Button btnSave = new Button("Guardar");
        btnSave.setOnAction(e -> {
            // Guardado
            FXGL.getDialogService().showMessageBox("Juego guardado (simulado).");
        });

        Button btnMainMenu = new Button("Salir al menú");
        btnMainMenu.setOnAction(e -> fireExitToMainMenu());

        Button btnExit = new Button("Salir al escritorio");
        btnExit.setOnAction(e -> fireExit());

        // Estilo base
        for (Button btn : new Button[]{btnResume, btnRestart, btnOptions, btnSave, btnMainMenu, btnExit}) {
            btn.setPrefWidth(200);
            btn.setStyle("-fx-font-size: 16px; -fx-background-color: rgba(0,0,0,0.7); -fx-text-fill: white;");
        }

        menuBox.getChildren().addAll(btnResume, btnRestart, btnOptions, btnSave, btnMainMenu, btnExit);

        root.getChildren().add(menuBox);
    }
}
