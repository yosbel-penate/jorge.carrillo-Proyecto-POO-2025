package App.EntityFactory;

import App.Components.AnimationComponents;
import App.Components.CombatStatsComponent;
import App.Game.GameApp;
import Domain.Entity.Characters.Players.EngineerMarcus;
import Domain.Entity.Characters.Players.JaxKane;
import Domain.Entity.Characters.Players.ZaraQuinn;
import Domain.Entity.Types;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import Domain.Entity.Characters.Players.Cyborg;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class PlayersFactory implements EntityFactory {

    Cyborg cyborg = new Cyborg();
    JaxKane jaxKane = new JaxKane();
    ZaraQuinn zaraQuinn = new ZaraQuinn();
    EngineerMarcus marcus = new EngineerMarcus();


    @Spawns("cyborg")
    public Entity newCyborg(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.PLAYER)
                .with(new CombatStatsComponent(cyborg.life,cyborg.atack,3,"cyborg"))
                .with(new AnimationComponents(
                        cyborg.name,
                        cyborg.cantidadFramesCaminando,
                        cyborg.cantidadFramesIdle,
                        cyborg.cantidadFramesAtack,
                        cyborg.anchoFrameAtack,
                        cyborg.altoFrameAtack,
                        cyborg.anchoFramesIdle,
                        cyborg.altoFramesIdle,
                        0,
                        jaxKane.cantidadFramesIdle - 1,
                        cyborg.cantidaFramesMuerte,
                        cyborg.anchoFramesMuerte,
                        cyborg.altoFramesMuerte,
                        cyborg.cantidadFramesAtackSpecial,
                        cyborg.anchoFramesAtackSpecial,
                        cyborg.altoFramesAtackSpecial,
                        cyborg.altoFramesCaminando,
                        cyborg.anchoFramesCaminando,
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
                        3,
                        "jaxKane"))
                .with(new AnimationComponents(
                        jaxKane.name,
                        jaxKane.cantidadFramesCaminando,
                        jaxKane.cantidadFramesIdle,
                        jaxKane.cantidadFramesAtack,
                        jaxKane.anchoFrameAtack,
                        jaxKane.altoFrameAtack,
                        jaxKane.anchoFramesIdle,
                        jaxKane.altoFramesIdle,
                        0,
                        jaxKane.cantidadFramesIdle - 1,
                        jaxKane.cantidaFramesMuerte,
                        jaxKane.anchoFramesMuerte,
                        jaxKane.altoFramesMuerte,
                        jaxKane.cantidadFramesAtackSpecial,
                        jaxKane.anchoFramesAtackSpecial,
                        jaxKane.altoFramesAtackSpecial,
                        jaxKane.altoFramesCaminando,
                        jaxKane.anchoFramesCaminando,
                        jaxKane.hitBox

                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("zara")
    public Entity newZara(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.PLAYER)
                .with(new CombatStatsComponent(zaraQuinn.life,
                        zaraQuinn.atack,
                        3,
                        "zaraQuinn"))
                .with(new AnimationComponents(
                        zaraQuinn.name,
                        zaraQuinn.cantidadFramesCaminando,
                        zaraQuinn.cantidadFramesIdle,
                        zaraQuinn.cantidadFramesAtack,
                        zaraQuinn.anchoFrameAtack,
                        zaraQuinn.altoFrameAtack,
                        zaraQuinn.anchoFramesIdle,
                        zaraQuinn.altoFramesIdle,
                        0,
                        zaraQuinn.cantidadFramesIdle - 1,
                        zaraQuinn.cantidaFramesMuerte,
                        zaraQuinn.anchoFramesMuerte,
                        zaraQuinn.altoFramesMuerte,
                        zaraQuinn.cantidadFramesAtackSpecial,
                        zaraQuinn.anchoFramesAtackSpecial,
                        zaraQuinn.altoFramesAtackSpecial,
                        zaraQuinn.altoFramesCaminando,
                        zaraQuinn.anchoFramesCaminando,
                        zaraQuinn.hitBox

                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Spawns("marcus")
    public Entity newMarcus(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .type(Types.EntityType.PLAYER)
                .with(new CombatStatsComponent(marcus.life,
                        marcus.atack,
                        3,
                        "engineerMarcus"))
                .with(new AnimationComponents(
                        marcus.name,
                        marcus.cantidadFramesCaminando,
                        marcus.cantidadFramesIdle,
                        marcus.cantidadFramesAtack,
                        marcus.anchoFrameAtack,
                        marcus.altoFrameAtack,
                        marcus.anchoFramesIdle,
                        marcus.altoFramesIdle,
                        0,
                        marcus.cantidadFramesIdle - 1,
                        marcus.cantidaFramesMuerte,
                        marcus.anchoFramesMuerte,
                        marcus.altoFramesMuerte,
                        marcus.cantidadFramesAtackSpecial,
                        marcus.anchoFramesAtackSpecial,
                        marcus.altoFramesAtackSpecial,
                        marcus.altoFramesCaminando,
                        marcus.anchoFramesCaminando,
                        marcus.hitBox

                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
}


