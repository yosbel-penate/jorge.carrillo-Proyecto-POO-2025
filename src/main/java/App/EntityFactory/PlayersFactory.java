package App.EntityFactory;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import Domain.Entity.Characters.Players.JaxKane;
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
    JaxKane jaxKane = new JaxKane();

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
    @Spawns("jaxKane")
    public Entity newJaxKane(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.PLAYER)
                .with(new CombatStatsComponent(jaxKane.life,
                        jaxKane.atack,
                        3))
                .with(new AnimationComponents(
                        jaxKane.name,
                        jaxKane.cantidadFramesIdle,
                        jaxKane.cantidadFramesAtack,
                        jaxKane.anchoFrameAtack,
                        jaxKane.altoFrameAtack,
                        jaxKane.anchoFramesIdle,
                        jaxKane.altoFramesIdle,
                        0,
                        cyborg.cantidadFramesIdle - 1,
                        jaxKane.cantidaFramesMuerte,
                        jaxKane.anchoFramesMuerte,
                        jaxKane.altoFramesMuerte,
                        jaxKane.cantidadFramesAtackSpecial,
                        jaxKane.anchoFramesAtackSpecial,
                        jaxKane.altoFramesAtackSpecial,
                        jaxKane.hitBox
                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}


