package View.UI;

import App.Components.AnimationComponents;
import com.almasb.fxgl.entity.Entity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static Domain.Settings.SettingsGame.TILE_SIZE;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class CombatModeUI {
    Image panelCombat;
    ImageView panelCombatView;
    Image atackImage;
    ImageView atackView;
    Button buttonAtack;

    public void showCombatUI(){

        //boton
        buttonAtack = new Button();
        buttonAtack.setPrefSize(100, 100);

        atackImage = getAssetLoader().loadImage("button_atacck.png");
        atackView = new ImageView();
        atackView.setImage(atackImage);
        atackView.setFitHeight(100);
        atackView.setFitWidth(100);
        buttonAtack.setGraphic(atackView);
        buttonAtack.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");


        //panel
        panelCombat = getAssetLoader().loadImage("combat_panel.png");
        panelCombatView = new ImageView();
        panelCombatView.setImage(panelCombat);
    }

    public void combatModeSettings(boolean combatModeStatus , Entity player, UI ui)
    {
        if (combatModeStatus){
            //panel
            getGameScene().addUINode(panelCombatView);
            panelCombatView.setTranslateX(TILE_SIZE);
            panelCombatView.setTranslateY(TILE_SIZE * 10);

            buttonAtack.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    player.getComponent(AnimationComponents.class).attack();
                    ui.reduceHealthEnemi(1);
                }
            });

            //boton
            getGameScene().addUINode(buttonAtack);
            buttonAtack.setTranslateX(TILE_SIZE * 3);
            buttonAtack.setTranslateY(TILE_SIZE * 10 + 10);

        }else {
            getGameScene().removeUINode(panelCombatView);
            getGameScene().removeUINode(buttonAtack);
        }
    }
}
