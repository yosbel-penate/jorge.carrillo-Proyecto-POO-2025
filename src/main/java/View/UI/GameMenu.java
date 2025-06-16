package View.UI;

import App.Services.MusicService;
import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class GameMenu extends FXGLDefaultMenu {

    public GameMenu() {
        super(MenuType.GAME_MENU);

        Pane root = getContentRoot();

        MusicService.playMainMenu();

        // Elimina nodos innecesarios del menú por defecto
        root.getChildren().removeIf(node -> node instanceof Rectangle);

        if (!root.getChildren().isEmpty() && root.getChildren().get(0) instanceof StackPane) {
            root.getChildren().remove(0);
        }

        if (root.getChildren().size() > 1 && root.getChildren().get(1) instanceof Pane) {
            root.getChildren().remove(1);
        }

        // Agrega fondo personalizado
        ImageView fondoView = FXGL.texture("fondoGameMenu.png", getAppWidth(), getAppHeight());
        fondoView.setFitWidth(getAppWidth());
        fondoView.setFitHeight(getAppHeight());
        fondoView.setPreserveRatio(false);
        fondoView.setX(0);
        fondoView.setY(0);

        root.getChildren().add(0, fondoView);
    }
}