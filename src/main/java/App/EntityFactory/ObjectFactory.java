package App.EntityFactory;

import App.Components.AnimationComponents;
import Domain.Entity.Types;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

public class ObjectFactory implements EntityFactory {

    //Entidades
    @Spawns("p")
    public Entity newExitTrigger(SpawnData data) {
        return FXGL.entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("object")
    public Entity newObject(SpawnData data) {

        return FXGL.entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("itemAtack")
    public Entity newItemAtack(SpawnData data) {

        return FXGL.entityBuilder(data)
                //.at(TILE_SIZE * 10, TILE_SIZE * 11)
                .type(Types.EntityType.ITEM_ATACCK)
                .with(new AnimationComponents(
                       "atack",
                        15,
                        750,
                        50,
                        0,
                        14,
                        11
                ))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("itemLife")
    public Entity newItemLife(SpawnData data) {

        return FXGL.entityBuilder(data)
                //.at(TILE_SIZE * 10, TILE_SIZE * 11)
                .type(Types.EntityType.ITEM_LIFE)
                .with(new AnimationComponents(
                        "life",
                        15,
                        750,
                        50,
                        0,
                        14,
                        11
                ))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }


}
