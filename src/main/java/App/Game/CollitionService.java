package App.Game;

import Domain.Entity.Types;
import View.UI.CombatModeUI;
import View.UI.UI;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class CollitionService
{
    boolean combatMode;

    public void enableCollitionEntities(Entity firstEntity,
                                        Entity secondEntity,
                                        CombatModeUI combatModeUI,
                                        UI ui)
    {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(Types.EntityType.CYBORG, Types.EntityType.ENEMY)
        {
            @Override
            protected void onCollisionBegin(Entity firtstEntity, Entity secondEntity) {
                combatMode = true;

                combatModeUI.combatModeSettings(combatMode,firtstEntity);
                MusicService.stopMusic();
                MusicService.battleMusic();
            }
            @Override
            protected void onCollision(Entity cyborg, Entity enemy) {
                // Anula cualquier desplazamiento residual del jugador
                PhysicsComponent pc = cyborg.getComponent(PhysicsComponent.class);
                pc.setLinearVelocity(0, 0);
                pc.overwritePosition(cyborg.getPosition());  // reposiciona en su lugar :contentReference[oaicite:6]{index=6}
            }

            protected void onCollisionEnd() {
                combatMode = false;

            }
        });
    }

    public void updateCollisionBox(Entity entity)
    {
        entity.getBoundingBoxComponent().clearHitBoxes();
        entity.getBoundingBoxComponent().addHitBox(new HitBox(BoundingShape.box(
                TILE_SIZE - 1,
                TILE_SIZE - 1)));
    }
}

