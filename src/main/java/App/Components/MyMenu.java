package App.Components;

import App.Game.MusicService;
import com.almasb.fxgl.app.scene.FXGLDefaultMenu;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// import correcto del DSL
import static com.almasb.fxgl.dsl.FXGL.texture;
import static com.almasb.fxgl.dsl.FXGLForKtKt.fire;

public class MyMenu extends FXGLDefaultMenu {

    public MyMenu() {
        super(MenuType.MAIN_MENU);

        Pane root = getContentRoot();
        // Cargar el CSS personalizado
        root.getStylesheets().add("assets/ui/cssStyle.css");

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

        //Agregar Fondo
        ImageView fondoView = FXGL.texture("fondo.png", getAppWidth(), getAppHeight());
        // Ajustar propiedades para cubrir toda la ventana
        fondoView.setFitWidth(getAppWidth());   // Ancho = ventana
        fondoView.setFitHeight(getAppHeight()); // Alto = ventana
        fondoView.setPreserveRatio(false);      // Permite estirar la imagen
        fondoView.setX(0);                      // Posición X inicial
        fondoView.setY(0);

        root.getChildren().add(0, fondoView);

    }
}