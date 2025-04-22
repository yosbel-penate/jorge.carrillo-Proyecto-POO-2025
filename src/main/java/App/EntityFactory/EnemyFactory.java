package App.EntityFactory;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import Domain.Entity.Characters.Enemies.Drone;
import Domain.Entity.Characters.Enemies.DroneTipe_1;
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
                .at(TILE_SIZE * 10, TILE_SIZE * 6)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(droneTipe1.life, droneTipe1.atack))
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
                .at(TILE_SIZE * 10, TILE_SIZE * 10)
                .type(Types.EntityType.ENEMY)
                .with(new CombatStatsComponent(drone.life, drone.atack))
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
                        650,
                        50,
                        12
                ))
                .with(physics)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}
