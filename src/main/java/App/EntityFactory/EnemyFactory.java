package App.EntityFactory;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import Domain.Entity.Characters.Enemies.*;
import Domain.Entity.Types;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import java.util.ArrayList;
import java.util.List;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class EnemyFactory implements EntityFactory {

    Drone drone = new Drone();
    DroneTipe_1 droneTipe1 = new DroneTipe_1();
    Paton paton = new Paton();

    //Entidades
    @Spawns("explore")
    public Entity newExplore(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(droneTipe1.life, droneTipe1.atack,3,"explore"))
                .with(new AnimationComponents(
                        "explore",
                        8,
                        4,
                        200,
                        50,
                        600,
                        49,
                        0,
                        7,
                        12,
                        600,
                        50,
                        11
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("paton")
    public Entity newPaton(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(Paton.life, Paton.atack,3,"paton"))
                .with(new AnimationComponents(
                        "paton",
                        paton.cantidadFrames,
                        paton.cantidadFramesAtackBasic,
                        Paton.anchoSpriteSheetAtackBasic,
                        Paton.altoSpriteSheetAtackBasic,
                        Paton.anchoSpriteSheet,
                        Paton.altoSpriteSheet,
                        0,
                        3,
                        Paton.cantidadFramesMuerte,
                        Paton.anchoFrameMuerte,
                        Paton.altoFrameMuerte,
                        11
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("patrulla")
    public Entity newPatrulla(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(Patrulla.life, Patrulla.atack,3,"patrulla"))
                .with(new AnimationComponents(
                        "patrulla",
                        Patrulla.cantidadFrames,
                        Patrulla.cantidadFramesAtackBasic,
                        Patrulla.anchoSpriteSheetAtackBasic,
                        Patrulla.altoSpriteSheetAtackBasic,
                        Patrulla.anchoSpriteSheet,
                        Patrulla.altoSpriteSheet,
                        0,
                        3,
                        Patrulla.cantidadFramesMuerte,
                        Patrulla.anchoFrameMuerte,
                        Patrulla.altoFrameMuerte,
                        11
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("droid1")
    public Entity newDroid1(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(droneTipe1.life, droneTipe1.atack,3,"droid1"))
                .with(new AnimationComponents(
                       "droid",
                        4,
                        12,
                        938,
                        50,
                        200,
                        50,
                        0,
                        3,
                        8,
                        400,
                        44,
                        11
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("droid2")
    public Entity newDroid2(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(drone.getLife(), drone.getAttack()))
                .with(new AnimationComponents(
                        "droid2",
                        4,
                        7,
                        800,
                        50,
                        400,
                        54,
                        0,
                        3,
                        12,
                        840,
                        50,
                        12
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("droid3")
    public Entity newDroid3(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(Drone3.life, Drone3.atack))
                .with(new AnimationComponents(
                        "droid3",
                        Drone3.cantidadFrames,
                        Drone3.cantidadFramesAtackBasic,
                        Drone3.anchoSpriteSheetAtackBasic,
                        Drone3.altoSpriteSheetAtackBasic,
                        Drone3.anchoSpriteSheet,
                        Drone3.altoSpriteSheet,
                        0,
                        Drone3.cantidadFrames - 1,
                        Drone3.cantidadFramesMuerte,
                        Drone3.anchoFrameMuerte,
                        Drone3.altoFrameMuerte,
                        12
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("boss")
    public Entity newBoss(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(17, 5))
                .with(new AnimationComponents(
                        "boss",
                        4,
                        8,
                        1557,
                        160,
                        543,
                        100,
                        0,
                        3,
                        Drone3.cantidadFramesMuerte,
                        Drone3.anchoFrameMuerte,
                        Drone3.altoFrameMuerte,
                        22
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }


    public static List<Entity> getEnemiesForLevel(String levelName) {
        List<Entity> entities = new ArrayList<>();

        int TILE_SIZE = 50; // o el valor que uses

        switch (levelName) {
            case "level_01":

                //boss
                entities.add(FXGL.spawn("boss", TILE_SIZE * 18, TILE_SIZE  * 7 ));

                // Tanques
                entities.add(FXGL.spawn("tanke", TILE_SIZE * 7, TILE_SIZE));
                entities.add(FXGL.spawn("tanke", TILE_SIZE * 9, TILE_SIZE));

                // Exploradores
                entities.add(FXGL.spawn("explore", TILE_SIZE * 20, TILE_SIZE * 10));
                entities.add(FXGL.spawn("explore", TILE_SIZE * 8, TILE_SIZE * 13));

                // Enemigos Droides
                entities.add(FXGL.spawn("droid1", TILE_SIZE * 10, TILE_SIZE * 10));
                entities.add(FXGL.spawn("droid1", TILE_SIZE * 28, TILE_SIZE * 15));

                entities.add(FXGL.spawn("droid2", TILE_SIZE * 17, TILE_SIZE * 15));
                entities.add(FXGL.spawn("droid2", TILE_SIZE * 12, TILE_SIZE * 4));
                entities.add(FXGL.spawn("droid2", TILE_SIZE * 28, TILE_SIZE * 5));

                entities.add(FXGL.spawn("droid3", TILE_SIZE * 18, TILE_SIZE * 5));
                entities.add(FXGL.spawn("droid3", TILE_SIZE * 18, TILE_SIZE * 20));

                entities.add(FXGL.spawn("paton", TILE_SIZE * 10, TILE_SIZE * 7));
                entities.add(FXGL.spawn("paton", TILE_SIZE * 24, TILE_SIZE * 13));


                entities.add(FXGL.spawn("patrulla", TILE_SIZE * 13, TILE_SIZE * 18));
                entities.add(FXGL.spawn("patrulla", TILE_SIZE * 20, TILE_SIZE));
                entities.add(FXGL.spawn("patrulla", TILE_SIZE * 30, TILE_SIZE * 3));



                break;

            case "level_02":
                entities.add(FXGL.spawn("tanke", TILE_SIZE * 7, TILE_SIZE));
                entities.add(FXGL.spawn("tanke", TILE_SIZE * 9, TILE_SIZE));

                // Exploradores
                entities.add(FXGL.spawn("explore", TILE_SIZE * 20, TILE_SIZE * 10));
                entities.add(FXGL.spawn("explore", TILE_SIZE * 8, TILE_SIZE * 13));

                // Enemigos Droides
                entities.add(FXGL.spawn("droid1", TILE_SIZE * 10, TILE_SIZE * 10));
                entities.add(FXGL.spawn("droid1", TILE_SIZE * 28, TILE_SIZE * 15));

                entities.add(FXGL.spawn("droid2", TILE_SIZE * 17, TILE_SIZE * 15));
                entities.add(FXGL.spawn("droid2", TILE_SIZE * 12, TILE_SIZE * 4));
                entities.add(FXGL.spawn("droid2", TILE_SIZE * 28, TILE_SIZE * 5));

                entities.add(FXGL.spawn("droid3", TILE_SIZE * 18, TILE_SIZE * 5));
                entities.add(FXGL.spawn("droid3", TILE_SIZE * 18, TILE_SIZE * 20));

                break;
        }

        return entities;
    }
}
