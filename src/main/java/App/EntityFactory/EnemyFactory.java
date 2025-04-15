package App.EntityFactory;

import App.Components.AnimationComponents;
import Domain.Entity.Types;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class EnemyFactory implements EntityFactory {
    @Spawns("boss")
    public Entity newBoss(SpawnData data){
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);

        return FXGL.entityBuilder(data)
                .at(TILE_SIZE * 6, TILE_SIZE * 6)
                .type(Types.EntityType.ENEMY)
                .with(new AnimationComponents(
                        "cyborg",
                        400,
                        50,
                        8
                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}
