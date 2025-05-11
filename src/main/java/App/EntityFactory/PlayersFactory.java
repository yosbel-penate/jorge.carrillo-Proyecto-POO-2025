package App.EntityFactory;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import Domain.Entity.Types;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import Domain.Entity.Characters.Players.Cyborg;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class PlayersFactory implements EntityFactory {

    Cyborg cyborg = new Cyborg();

    @Spawns("cyborg")
    public Entity newCyborg(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .at(TILE_SIZE * 5, TILE_SIZE * 4)
                .type(Types.EntityType.PLAYER)
                .with(new CombatStatsComponent(cyborg.life,cyborg.atack,3))
                .with(new AnimationComponents(
                        cyborg.name,
                        cyborg.cantidadFramesIdle,
                        cyborg.cantidadFramesAtack,
                        cyborg.anchoFrameAtack,
                        cyborg.altoFrameAtack,
                        cyborg.anchoFramesIdle,
                        cyborg.altoFramesIdle,
                        0,
                        cyborg.cantidadFramesIdle - 1,
                        cyborg.cantidaFramesMuerte,
                        cyborg.anchoFramesMuerte,
                        cyborg.altoFramesMuerte,
                        cyborg.cantidadFramesAtackSpecial,
                        cyborg.anchoFramesAtackSpecial,
                        cyborg.altoFramesAtackSpecial,
                        cyborg.hitBox
                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
 }


