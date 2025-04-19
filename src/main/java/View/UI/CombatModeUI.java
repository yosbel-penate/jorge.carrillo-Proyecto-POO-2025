package View.UI;

import App.Components.AnimationComponents;
import Domain.Entity.Characters.Players.Cyborg;
import Domain.GameApp.LogicCombat;
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

    LogicCombat combatStats = new LogicCombat();
    private UI ui;

    public CombatModeUI(UI ui){
        this.ui = ui;
    }

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

    public void combatModeSettings(boolean combatModeStatus , Entity player) {
        if (combatModeStatus){

            getGameScene().addUINode(panelCombatView);
            panelCombatView.setTranslateX(TILE_SIZE);
            panelCombatView.setTranslateY(TILE_SIZE * 10);

            buttonAtack.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    player.getComponent(AnimationComponents.class).attack();
                    reduceHealthEnemi(Cyborg.atack);
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

    public void updateHealthBarPlayer() {
        for (int i = 0; i < combatStats.maxHealthPlayer; i++) {
            ImageView heartView = (ImageView) ui.heartsBarEnemiHbox.getChildren().get(i);
            if (i < combatStats.currentHealthPlayer) {
                heartView.setVisible(true);
                // Opcional: resetear la imagen, por si se modificó
                heartView.setImage(ui.lifePointPlayer);
            } else {
                heartView.setVisible(false);
            }
        }
    }

    public void updateHealthBarEnemi() {
        for (int i = 0; i < combatStats.maxHealthEnemi; i++) {
            ImageView heartView = (ImageView) ui.heartsBarEnemiHbox.getChildren().get(i);
            if (i < combatStats.currentHealthEnemi) {
                heartView.setVisible(true);
                // Opcional: resetear la imagen, por si se modificó
                heartView.setImage(ui.lifePointEnemi);
            } else {
                heartView.setVisible(false);
            }
        }
    }

    public void setHealthEnemi(int health) {
        combatStats.currentHealthEnemi = Math.max(0, Math.min(health, combatStats.maxHealthEnemi));
        updateHealthBarEnemi();
    }

    public void setHealthPLayer(int health) {
        combatStats.currentHealthPlayer = Math.max(0, Math.min(health, combatStats.maxHealthPlayer));
        updateHealthBarEnemi();
    }

    public void reduceHealthPlayer(int amount) {
        combatStats.currentHealthPlayer -= amount;
        if (combatStats.currentHealthPlayer < 0)
            combatStats.currentHealthPlayer = 0;
        updateHealthBarPlayer();
    }

    public void reduceHealthEnemi(int amount) {
        combatStats.currentHealthEnemi -= amount;
        if (combatStats.currentHealthEnemi < 0)
            combatStats.currentHealthEnemi = 0;
        updateHealthBarEnemi();
    }

    public void reduceStepPoint() {
        if (combatStats.numeroActualPasos < combatStats.setPLayerInitSteeps()) {
            // Se obtiene el ImageView correspondiente al paso a descontar
            ImageView pasoIcon = (ImageView) ui.playerSteepHbox.getChildren().get(combatStats.numeroActualPasos);
            // Se puede optar por ocultarlo o cambiarle la opacidad para dar efecto de "gastado"
            pasoIcon.setVisible(false);
            // Alternativamente, si no deseas ocultar por completo puedes reducir la opacidad:
            // pasoIcon.setOpacity(0.3);
            combatStats.numeroActualPasos++;
        }
    }

    public void reduceEnemyStepPoint() {
        if (combatStats.enemyConsumedSteps < combatStats.enemyMaxSteps) {
            // Obtén el ícono correspondiente a ese paso y cambia su visibilidad o su opacidad
            ImageView enemyStepIcon = (ImageView) ui.enemyStepsHbox.getChildren().get(combatStats.enemyConsumedSteps);
            enemyStepIcon.setVisible(false); // O, alternativamente: enemyStepIcon.setOpacity(0.3);
            combatStats.enemyConsumedSteps++;
        }
    }

}
