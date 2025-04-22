package App.Components;

import App.Game.CollitionService;
import App.Game.MusicService;
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

    int hitBox;
    double animVelocity = 0.7;
    CollitionService collitionService = new CollitionService();
    private AnimatedTexture  texture;
    private AnimationChannel animIdle,
                             animWalk,
                             animAbajo,
                             animArriba,
                             animAtackBasic,
                             animAtackEspecial,
                             animMuerte;

    //===================Contructores====================
    //Contructor para Imagenes Dinamicas(items)
    public AnimationComponents(String nameItem,
                               int cantidadFrames,
                               int anchoImagen,
                               int altoImagen,
                               int frameInicio,
                               int frameFinal,
                               int hitBox) {
        this.hitBox = hitBox;
        animIdle = new AnimationChannel(FXGL.image(nameItem + "item.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(animVelocity),
                0,
                cantidadFrames - 1);
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
                               int frameFinal,
                               int cantidadFramesMuerte,
                               int anchoMuerte,
                               int altoMuerte,
                               int hitBox) {
        this.hitBox = hitBox;
        animAtackBasic = new AnimationChannel(FXGL.image(nameCharacter + "atack_basic.png"),
                cantidadFramesAtack,
                anchoImagenAtack / cantidadFramesAtack,
                altoImagenAtack,
                Duration.seconds(animVelocity),
                0,
                cantidadFramesAtack - 1);

        animIdle = new AnimationChannel(FXGL.image(nameCharacter + "idle.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(animVelocity),
                0,
                cantidadFrames - 1);

        animWalk = new AnimationChannel(FXGL.image(nameCharacter + "caminando.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(animVelocity),
                frameInicio,
                frameFinal);

        animAbajo = new AnimationChannel(FXGL.image(nameCharacter + "adelante.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(animVelocity),
                frameInicio,
                frameFinal);

        animArriba = new AnimationChannel(FXGL.image(nameCharacter + "atras.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(animVelocity),
                frameInicio,
                frameFinal);
        animMuerte = new AnimationChannel(FXGL.image(nameCharacter + "deat.png"),
                cantidadFramesMuerte,
                anchoMuerte / cantidadFramesMuerte,
                altoMuerte,
                Duration.seconds(animVelocity),
                0,
                cantidadFramesMuerte - 1);

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
                Duration.seconds(animVelocity),
                0,
                7);


        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded()
    {
        entity.getTransformComponent().setScaleOrigin(new Point2D(25, 25));
        entity.getViewComponent().addChild(texture);
        selectTypeOfHitBox(hitBox);
        texture.loopAnimationChannel(animIdle);
    }

    public void selectTypeOfHitBox(int hitBox){
        if (hitBox == 11){
            collitionService.updateCollisionBox1x1(this.entity);
        } else if (hitBox == 12) {
            collitionService.updateCollisionBox1x2(this.entity);
        }else {
            collitionService.updateCollisionBox2x2(this.entity);
        }
    }

    @Override
    public void onUpdate(double tpf) {}

    // Modifica el método moveEntity
    private void moveEntity(double directionX, double directionY, AnimationChannel channel, double scaleX) {
        double duration = animVelocity; // 0.5 segundos por tile

        // Ajustar velocidad basada en duración y tpf
        double speedX = (TILE_SIZE / duration) * directionX;
        double speedY = (TILE_SIZE / duration) * directionY;

        PhysicsComponent physics = entity.getComponent(PhysicsComponent.class);
        physics.setLinearVelocity(speedX, speedY);

        // Configurar animación para que coincida con la duración del movimiento
        texture.loopAnimationChannel(channel);
        entity.setScaleX(scaleX);

        FXGL.runOnce(() -> {
            physics.setLinearVelocity(0, 0);
            snapToGrid();
            texture.loopAnimationChannel(animIdle);
        }, Duration.seconds(duration));
    }
    public void moveUp() {
        moveEntity(0, -1, animArriba, 1);
    }

    public void moveDown() {
        moveEntity(0, 1, animAbajo, 1);
    }

    public void moveLeft() {
        moveEntity(-1, 0, animWalk, -1);
    }

    public void moveRight() {
        moveEntity(1, 0, animWalk, 1);
    }

    private void snapToGrid() {
        double newX = Math.round(entity.getX() / TILE_SIZE) * TILE_SIZE;
        double newY = Math.round(entity.getY() / TILE_SIZE) * TILE_SIZE;
        entity.setPosition(newX, newY);
    }
    public void stopMoving() {
        if (texture.getAnimationChannel() != animIdle) {
            texture.loopAnimationChannel(animIdle);
        }
    }

    //Otras animaciones
    public void playAttackAnimation() {
        // Reproducir la animación de ataque una vez
        texture.playAnimationChannel(animAtackBasic);


        // Programar el retorno a la animación idle después de 0.5 segundos
        FXGL.getGameTimer().runOnceAfter(() -> {

            texture.loopAnimationChannel(animIdle);
            //FXGL.getAudioPlayer().stopAllMusic();
            MusicService.playBattle();

        }, Duration.seconds(animVelocity));
    }

    public void playDeatAnimation()
    {
        if (animMuerte != null){
            texture.playAnimationChannel(animMuerte);
        }
    }
}
