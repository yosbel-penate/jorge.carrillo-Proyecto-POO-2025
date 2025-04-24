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

    @Spawns("itemSpecialPoint")
    public Entity newItemSpecialPoint(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ITEM_SPECIAL_POINT)
                .with(new AnimationComponents(
                        "generatorSpecialPoint",
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

    @Spawns("barrier")
    public Entity newBarrier(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);

        return FXGL.entityBuilder(data)

                .type(Types.EntityType.BARRIER)
                .with(new AnimationComponents(
                        "barrera",
                        18,
                        900,
                        150,
                        0,
                        17,
                        13
                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("barrierDisabled")
    public Entity newBarrierDisabled(SpawnData data) {


        return FXGL.entityBuilder(data)

                .with(new AnimationComponents(
                        "barreraDesactivada",
                        18,
                        900,
                        150,
                        0,
                        17,
                        13
                ))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("controlPanel")
    public Entity newControlPanel(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);

        return FXGL.entityBuilder(data)

                .type(Types.EntityType.PANEL)
                .with(new AnimationComponents(
                        "panel",
                        20,
                        1000,
                        50,
                        0,
                        19,
                        11
                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

}
