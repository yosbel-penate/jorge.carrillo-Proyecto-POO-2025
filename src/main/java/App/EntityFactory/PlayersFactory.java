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
import Domain.Entity.Characters.Players.Cyborg;

import static Domain.Settings.SettingsGame.TILE_SIZE;

public class PlayersFactory implements EntityFactory {

    @Spawns("Cyborg")
    public Entity newCyborg(SpawnData data) {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        return FXGL.entityBuilder(data)
                .at(TILE_SIZE * 5, TILE_SIZE * 4)
                .type(Types.EntityType.CYBORG)
                .with(new AnimationComponents(
                        "cyborg",
                        Cyborg.cantidadFrames,
                        Cyborg.cantidadFramesAtackBasic,
                        Cyborg.anchoSpriteSheetAtackBasic,
                        Cyborg.altoSpriteSheetAtackBasic,
                        Cyborg.anchoSpriteSheet,
                        Cyborg.altoSpriteSheet,
                        0,
                        Cyborg.cantidadFrames - 1,
                        6,
                        300,
                        50

                ))
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }
 }


