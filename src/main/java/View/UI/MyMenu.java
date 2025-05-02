package View.UI;

import App.Services.MusicService;
import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class MyMenu extends FXGLDefaultMenu {

    public MyMenu() {
        super(MenuType.MAIN_MENU);

        Pane root = getContentRoot();

        //Elimina el nodo del fondo por defecto
        root.getChildren().removeIf(node -> node instanceof Rectangle);

        MusicService.playMainMenu();

        //Elimina el nodo del StackPane
        if (!root.getChildren().isEmpty() && root.getChildren().get(0) instanceof StackPane) {
            root.getChildren().remove(0);
        }

        //Elimina el nodo de efecto grafico
        if (root.getChildren().size() > 1 && root.getChildren().get(1) instanceof Pane) {
            root.getChildren().remove(1);
        }

        // Elimina el nodo del título "v0.0-DEVELOPER" (es un nodo Text)
        root.getChildren().removeIf(node -> {
            if (node instanceof Text) {
                String text = ((Text) node).getText();
                return text.equals("v0.0-DEVELOPER");
            }
            return false;
        });

        //Fondo
        ImageView fondoView = FXGL.texture("fondo.png");
        root.getChildren().add(0, fondoView);

    }
}