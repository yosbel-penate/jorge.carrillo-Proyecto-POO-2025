package App.Services;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import View.UI.CombatModeUI;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class Input {

    private boolean canMove = true;

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void movInput() {


        getInput().addAction(new UserAction("Mover Arriba") {
            @Override
            protected void onActionBegin() {
                if (canMove){
                    Entity character = GameApp.currentEntity;

                    character.getComponent(AnimationComponents.class).moveUp();
                }
            }

            @Override
            protected void onActionEnd() {
                if (canMove){
                    Entity character = GameApp.currentEntity;

                    character.getComponent(AnimationComponents.class).stopMoving();
                }
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Mover Abajo") {
            @Override
            protected void onActionBegin() {
                if (canMove){
                    Entity character = GameApp.currentEntity;

                    character.getComponent(AnimationComponents.class).moveDown();
                }
            }

            @Override
            protected void onActionEnd() {
                Entity character = GameApp.currentEntity;

                character.getComponent(AnimationComponents.class).stopMoving();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Mover Izquierda") {
            @Override
            protected void onActionBegin() {
                if (canMove){
                    Entity character = GameApp.currentEntity;

                    character.getComponent(AnimationComponents.class).moveLeft();
                }
            }

            @Override
            protected void onActionEnd() {
                Entity character = GameApp.currentEntity;

                character.getComponent(AnimationComponents.class).stopMoving();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Mover Derecha") {
            @Override
            protected void onActionBegin() {
                if (canMove){
                    Entity character = GameApp.currentEntity;

                    character.getComponent(AnimationComponents.class).moveRight();

                }
            }
            @Override
            protected void onActionEnd() {
                Entity character = GameApp.currentEntity;

                character.getComponent(AnimationComponents.class).stopMoving();
            }
        }, KeyCode.D);
    }
}
