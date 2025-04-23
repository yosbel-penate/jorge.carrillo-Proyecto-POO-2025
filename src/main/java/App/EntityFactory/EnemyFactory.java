package App.EntityFactory;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import Domain.Entity.Characters.Enemies.Drone;
import Domain.Entity.Characters.Enemies.Drone3;
import Domain.Entity.Characters.Enemies.DroneTipe_1;
import Domain.Entity.Characters.Enemies.Golem;
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
    //Instancias :
    Drone drone = new Drone();
    DroneTipe_1 droneTipe1 = new DroneTipe_1();

    //Entidades
    @Spawns("droid1")
    public Entity newDroid1(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(droneTipe1.life, droneTipe1.atack,3))
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
    @Spawns("golem")
    public Entity newGolem(SpawnData data){

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(Golem.life, Golem.atack))
                .with(new AnimationComponents(
                        "golem", Golem.cantidadFrames,
                Golem.cantidadFramesAtackBasic,
                Golem.anchoSpriteSheetAtackBasic,
                Golem.altoSpriteSheetAtackBasic,
                Golem.anchoSpriteSheet,
                Golem.altoSpriteSheet,
                0,
                Golem.cantidadFrames - 1,
                Golem.cantidadFramesMuerte,
                Golem.anchoFrameMuerte,
                Golem.altoFrameMuerte,
                22
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
}
