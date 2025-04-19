package App;

import App.Components.AnimationComponents;
import View.UI.CombatModeUI;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;

public class Input {

    public void movInput(Entity character , CombatModeUI combatModeUI) {

        getInput().addAction(new UserAction("Mover Arriba") {
            @Override
            protected void onActionBegin() {
                character.getComponent(AnimationComponents.class).moveUp();
            }

            @Override
            protected void onActionEnd()
            {
                character.getComponent(AnimationComponents.class).stopMoving();
                combatModeUI.reduceStepPoint();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Mover Abajo") {
            @Override
            protected void onActionBegin() {
                character.getComponent(AnimationComponents.class).moveDown();
            }

            @Override
            protected void onActionEnd()
            {
                character.getComponent(AnimationComponents.class).stopMoving();
                combatModeUI.reduceStepPoint();
            }
        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("Mover Izquierda") {
            @Override
            protected void onActionBegin()
            {
                character.getComponent(AnimationComponents.class).moveLeft();
            }

            @Override
            protected void onActionEnd()
            {
                character.getComponent(AnimationComponents.class).stopMoving();
                combatModeUI.reduceStepPoint();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Mover Derecha") {
            @Override
            protected void onActionBegin() {
                character.getComponent(AnimationComponents.class).moveRight();
            }

            @Override
            protected void onActionEnd()
            {
                character.getComponent(AnimationComponents.class).stopMoving();
                combatModeUI.reduceStepPoint();
            }
        }, KeyCode.RIGHT);
    }
}
