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

import java.util.ArrayList;
import java.util.List;

import static Domain.Settings.SettingsGame.TILE_SIZE;

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

    @Spawns("coin")
    public Entity newCoin(SpawnData data) {

        return FXGL.entityBuilder(data)
                //.at(TILE_SIZE * 10, TILE_SIZE * 11)
                .type(Types.EntityType.COIN)
                .with(new AnimationComponents(
                        "coin",
                        9,
                        270,
                        30,
                        0,
                        8,
                        00
                ))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("tanke")
    public Entity newTanke(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ITEM_ATACCK)
                .with(new AnimationComponents(
                        "tanke",
                        4,
                        201,
                        68,
                        0,
                        2,
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
                        12
                ))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("door")
    public Entity newBarrierDoor(SpawnData data) {

        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.STATIC);

        return FXGL.entityBuilder(data)

                .type(Types.EntityType.DOOR)

                .with(new AnimationComponents(
                        "puertaNivel",
                        6,
                        600,
                        50,
                        0,
                        5,
                        12,
                        1200,
                        50,
                        12
                ))
                .with(physicsComponent)
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

    public static List<Entity> getObjectsForLevel(String levelName) {
        List<Entity> entities = new ArrayList<>();

        int TILE_SIZE = 50; // o el valor que uses

        switch (levelName) {
            case "level_01":

                // Monedas
                entities.add(FXGL.spawn("coin", TILE_SIZE * 6 + 10, TILE_SIZE * 2 + 10));
                entities.add(FXGL.spawn("coin", TILE_SIZE * 20 + 10, TILE_SIZE * 12 + 10));
                entities.add(FXGL.spawn("coin", TILE_SIZE * 11 + 10, TILE_SIZE * 12 + 10));

                // Barreras
                entities.add(FXGL.spawn("barrierDisabled", TILE_SIZE * 16, TILE_SIZE * 13));
                entities.add(FXGL.spawn("barrierDisabled", TILE_SIZE * 22, TILE_SIZE * 13));
                entities.add(FXGL.spawn("barrierDisabled", TILE_SIZE * 22, TILE_SIZE * 23));
                entities.add(FXGL.spawn("barrierDisabled", TILE_SIZE * 16, TILE_SIZE * 23));

                // Paneles de control
                entities.add(FXGL.spawn("controlPanel", TILE_SIZE * 15, TILE_SIZE * 2));
                entities.add(FXGL.spawn("controlPanel", TILE_SIZE * 23, TILE_SIZE * 2));

                // Ítems
                entities.add(FXGL.spawn("itemSpecialPoint", TILE_SIZE * 5, TILE_SIZE * 7));
                entities.add(FXGL.spawn("itemSpecialPoint", TILE_SIZE * 25, TILE_SIZE * 5));

                entities.add(FXGL.spawn("itemLife", TILE_SIZE * 19, TILE_SIZE * 10));
                entities.add(FXGL.spawn("itemLife", TILE_SIZE * 3, TILE_SIZE * 13));

                entities.add(FXGL.spawn("itemAtack", TILE_SIZE * 9, TILE_SIZE * 4));
                entities.add(FXGL.spawn("itemAtack", TILE_SIZE * 19, TILE_SIZE * 15));

                break;

            case "level_02":

                entities.add(FXGL.spawn("itemSpecialPoint", TILE_SIZE * 23, TILE_SIZE * 18));
                entities.add(FXGL.spawn("itemSpecialPoint", TILE_SIZE * 28, TILE_SIZE * 7));

                entities.add(FXGL.spawn("itemLife", TILE_SIZE * 20, TILE_SIZE * 23));
                entities.add(FXGL.spawn("itemLife", TILE_SIZE * 13, TILE_SIZE * 13));

                entities.add(FXGL.spawn("itemAtack", TILE_SIZE * 11, TILE_SIZE * 27));
                entities.add(FXGL.spawn("itemAtack", TILE_SIZE * 17, TILE_SIZE * 3));

                // Monedas
                entities.add(FXGL.spawn("coin", TILE_SIZE * 6 + 10, TILE_SIZE * 2 + 10));
                entities.add(FXGL.spawn("coin", TILE_SIZE * 20 + 10, TILE_SIZE * 8 + 10));
                entities.add(FXGL.spawn("coin", TILE_SIZE * 11 + 10, TILE_SIZE * 12 + 10));
                entities.add(FXGL.spawn("coin", TILE_SIZE * 8 + 10, TILE_SIZE * 28 + 10));
                entities.add(FXGL.spawn("coin", TILE_SIZE * 18 + 10, TILE_SIZE * 28 + 10));

                break;

            case "level_03":


                break;
        }

        return entities;

    }
}
