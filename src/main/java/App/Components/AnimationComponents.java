package App.Components;

import App.Game.CollitionService;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import static Domain.Settings.SettingsGame.TILE_SIZE;

public class AnimationComponents extends Component
{


    CollitionService collitionService = new CollitionService();
    private AnimatedTexture  texture;
    private AnimationChannel animIdle,
                             animWalk,
                             animAbajo,
                             animArriba,
                             animAtackBasic,
                             animAtackEspecial;

    //===================Contructores====================
    //Contructor para Imagenes Dinamicas(Varios Frames)
    public AnimationComponents(String nameCharacter,
                               int cantidadFrames,
                               int anchoImagen,
                               int altoImagen,
                               int frameInicio,
                               int frameFinal) {
        animIdle = new AnimationChannel(FXGL.image(nameCharacter + "idle.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                0,
                7);

        animWalk = new AnimationChannel(FXGL.image(nameCharacter + "caminando.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                frameInicio,
                frameFinal);

        animAbajo = new AnimationChannel(FXGL.image(nameCharacter + "adelante.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                frameInicio,
                frameFinal);

        animArriba = new AnimationChannel(FXGL.image(nameCharacter + "atras.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                frameInicio,
                frameFinal);

        texture = new AnimatedTexture(animIdle);
    }
    //Contructor para Imagenes Dinamicas con animaciones de ataque
    public AnimationComponents(String nameCharacter,
                               int cantidadFrames,
                               int cantidadFramesAtack,
                               int anchoImagenAtack,
                               int altoImagenAtack,
                               int anchoImagen,
                               int altoImagen,
                               int frameInicio,
                               int frameFinal) {
        animAtackBasic = new AnimationChannel(FXGL.image(nameCharacter + "atack_basic.png"),
                cantidadFramesAtack,
                anchoImagenAtack / cantidadFramesAtack,
                altoImagenAtack,
                Duration.seconds(0.5),
                0,
                cantidadFramesAtack - 1);

        animIdle = new AnimationChannel(FXGL.image(nameCharacter + "idle.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                0,
                7);

        animWalk = new AnimationChannel(FXGL.image(nameCharacter + "caminando.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                frameInicio,
                frameFinal);

        animAbajo = new AnimationChannel(FXGL.image(nameCharacter + "adelante.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                frameInicio,
                frameFinal);

        animArriba = new AnimationChannel(FXGL.image(nameCharacter + "atras.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                frameInicio,
                frameFinal);

        texture = new AnimatedTexture(animIdle);
    }

    //Contructor para Imagenes Estaticas(Un solo frame)
    public AnimationComponents(String nameCharacter,
                               int anchoImagen,
                               int altoImagen,
                               int cantidadFrames)
    {
        animIdle = new AnimationChannel(FXGL.image(nameCharacter + "idle.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(0.5),
                0,
                7);


        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded()
    {
        entity.getTransformComponent().setScaleOrigin(new Point2D(25, 25));
        entity.getViewComponent().addChild(texture);
        collitionService.updateCollisionBox(this.entity);
        texture.loopAnimationChannel(animIdle);
    }

    @Override
    public void onUpdate(double tpf) {}

    //Metodo General Para Movimiento con animacion
    private void moveEntity(double velocityX, double velocityY, AnimationChannel channel, double scaleX) {
        // Cambiar la animación sólo si es necesario.
        if (texture.getAnimationChannel() != channel) {
            texture.loopAnimationChannel(channel);
        }

        // Aplicar la orientación (voltear o no el sprite).
        entity.setScaleX(scaleX);

        // Obtener el componente de física para asignar velocidad.
        PhysicsComponent physics = entity.getComponent(PhysicsComponent.class);
        double duration = 0.5;
        physics.setLinearVelocity(velocityX, velocityY);

        // Programar la parada y la alineación a la rejilla.
        FXGL.runOnce(() -> {
            physics.setLinearVelocity(0, 0);
            // Alinear en X si se mueve horizontalmente
            if (velocityX != 0) {
                double newX = Math.round(entity.getX() / TILE_SIZE) * TILE_SIZE;
                entity.setX(newX);
            }
            // Alinear en Y si se mueve verticalmente
            if (velocityY != 0) {
                double newY = Math.round(entity.getY() / TILE_SIZE) * TILE_SIZE;
                entity.setY(newY);
            }
        }, Duration.seconds(duration));
    }

    //Metodos especificos para movimiento con animacion
    public void moveUp() {
        double velocity = ((double) TILE_SIZE) / 0.5;
        moveEntity(0, -velocity, animArriba, 1);
    }

    public void moveDown() {
        double velocity = ((double) TILE_SIZE) / 0.5;
        moveEntity(0, velocity, animAbajo, 1);
    }

    public void moveLeft() {
        double velocity = ((double) TILE_SIZE) / 0.5;
        moveEntity(-velocity, 0, animWalk, -1);
    }

    public void moveRight() {
        double velocity = ((double) TILE_SIZE) / 0.5;
        moveEntity(velocity, 0, animWalk, 1);
    }

    public void stopMoving() {
        if (texture.getAnimationChannel() != animIdle) {
            texture.loopAnimationChannel(animIdle);
        }
    }

    //Otras animaciones
    public void attack() {
        // Reproducir la animación de ataque una vez
        texture.playAnimationChannel(animAtackBasic);
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("arma.wav"));


        // Programar el retorno a la animación idle después de 0.5 segundos
        FXGL.getGameTimer().runOnceAfter(() -> {
            texture.loopAnimationChannel(animIdle);
            FXGL.getAudioPlayer().stopAllMusic();
            FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("loading.wav"));

        }, Duration.seconds(0.5));
    }
}
