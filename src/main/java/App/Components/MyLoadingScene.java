package App.Components;

import com.almasb.fxgl.app.scene.FXGLLoadingScene;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.util.Duration;

import com.almasb.fxgl.app.scene.FXGLLoadingScene;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppWidth;

public class MyLoadingScene extends LoadingScene {

    public MyLoadingScene() {
        // Configurar elementos visuales personalizados
        getContentRoot().setStyle("-fx-background-color: #000000;"); // Fondo negro

        // Añadir tu logo o imagen (ej: "loading_logo.png" en assets/textures/)
        var logo = FXGL.texture("loading_logo.png", 300, 300);
        logo.setTranslateX(getAppWidth() / 2 - 150);
        logo.setTranslateY(getAppHeight() / 2 - 150);

        // Barra de progreso personalizada
        var progressBar = new ProgressBar();
        progressBar.setTranslateX(getAppWidth() / 2 - 200);
        progressBar.setTranslateY(getAppHeight() / 2 + 100);

        getContentRoot().getChildren().addAll(logo, progressBar);
    }



}