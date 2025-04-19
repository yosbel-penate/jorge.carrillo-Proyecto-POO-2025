package App.Components;

import View.UI.CombatModeUI;
import View.UI.UI;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.util.Duration;
import javafx.geometry.Point2D;

public class EnemyController extends Component {

    private Entity player;
    private int tileSize;
    private boolean isMoving = false;
    private int actionPoints = 5; // Pasos por turno
    private CombatModeUI ui;

    private PhysicsComponent physics;
    private AnimationComponents anim;

    public EnemyController(Entity player, CombatModeUI ui, int tileSize) {
        this.player = player;
        this.tileSize = tileSize;
        this.ui = ui;
    }

    @Override
    public void onAdded() {
        physics = entity.getComponent(PhysicsComponent.class);
        anim = entity.getComponent(AnimationComponents.class);
    }

    @Override
    public void onUpdate(double tpf) {
        if (!isMoving && actionPoints > 0) {
            moveTowardsPlayer();
        }
    }

    private void moveTowardsPlayer() {
        int ex = (int)(entity.getX() / tileSize);
        int ey = (int)(entity.getY() / tileSize);
        int px = (int)(player.getX() / tileSize);
        int py = (int)(player.getY() / tileSize);

        int dx = px - ex;
        int dy = py - ey;

        if ((dx | dy) == 0) return;
        int moveX = 0;
        int moveY = 0;

        // Priorizar el movimiento en el eje X si la distancia en X es mayor
        if (Math.abs(dx) > Math.abs(dy)) {
            moveX = Integer.signum(dx);
        } else {
            moveY = Integer.signum(dy);
        }

        Point2D targetPosition = entity.getPosition().add(moveX * tileSize, moveY * tileSize);

        if (!isPositionFree(targetPosition)) {
            return;
        }

        if (!isMoving && actionPoints > 0) {
            isMoving     = true;
            actionPoints--;
            ui.reduceEnemyStepPoint();

            if (dx != 0)
            {
                if (dx > 0)
                {
                    anim.moveRight();
                }else {
                    anim.moveLeft();
                }
            }else {
                if (dy > 0)
                {
                    anim.moveDown();
                }else {
                    anim.moveUp();
                }
            }

            FXGL.getGameTimer()
                    .runOnceAfter(() -> {
                        isMoving = false;
                        anim.stopMoving();
                    }, Duration.seconds(1));
        }
    }

    private boolean isPositionFree(Point2D position) {
        // Convertimos la posición objetivo en coordenadas de tile
        int targetTileX = (int) (position.getX() / tileSize);
        int targetTileY = (int) (position.getY() / tileSize);

        // Obtenemos la posición del jugador también como tile
        int playerTileX = (int) (player.getX() / tileSize);
        int playerTileY = (int) (player.getY() / tileSize);

        // Si el tile destino es el mismo donde está el jugador, no es libre
        if (targetTileX == playerTileX && targetTileY == playerTileY) {
            return false;
        }

        // Podés agregar aquí más lógica para otros obstáculos si querés

        return true; // Si no hay conflicto, la posición está libre
    }
}
