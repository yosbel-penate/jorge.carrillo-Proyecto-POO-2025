package App.Components;

import App.Services.CollitionService;
import App.Services.MusicService;
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
    boolean isMoving;
    double animVelocity = 0.7;
    CollitionService collitionService = new CollitionService();
    private AnimatedTexture  texture;
    private AnimationChannel animIdle,
                             animWalk,
                             animAbajo,
                             animArriba,
                             animAtackBasic,
                             animAtackEspecial,
                             animMuerte,
                             animDisableBarrier;

    //Constructor para los Items y otros objetos de un solo canal de animacion
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

        animDisableBarrier = new AnimationChannel(FXGL.image(nameItem + "disabled.png"),
                cantidadFrames,
                anchoImagen / cantidadFrames,
                altoImagen,
                Duration.seconds(1),
                0,
                cantidadFrames - 1);
        texture = new AnimatedTexture(animIdle);
    }

    //Contructor para Imagenes Dinamicas con animaciones de Enemigos
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

    //Constructor para Players
    public AnimationComponents(String nameCharacter,
                               int cantidadFramesCaminando,
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
                               int cantidadFramesSpecialAtack,
                               int anchoAtackSpecialFrame,
                               int altoAtackSpecialFrame,
                               int altoFramesCaminando,
                               int anchoFramesCaminando,
                               int hitBox) {
        this.hitBox = hitBox;
        animAtackEspecial = new AnimationChannel(FXGL.image(nameCharacter + "atack_special.png"),
                cantidadFramesSpecialAtack,
                anchoAtackSpecialFrame / cantidadFramesSpecialAtack,
                altoAtackSpecialFrame,
                Duration.seconds(animVelocity),
                0,
                cantidadFramesSpecialAtack - 1);


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
                cantidadFramesCaminando,
                anchoFramesCaminando / cantidadFramesCaminando,
                altoFramesCaminando,
                Duration.seconds(animVelocity),
                frameInicio,
                frameFinal);

        animAbajo = new AnimationChannel(FXGL.image(nameCharacter + "adelante.png"),
                cantidadFramesCaminando,
                anchoFramesCaminando / cantidadFramesCaminando,
                altoFramesCaminando,
                Duration.seconds(animVelocity),
                frameInicio,
                frameFinal);

        animArriba = new AnimationChannel(FXGL.image(nameCharacter + "atras.png"),
                cantidadFramesCaminando,
                anchoFramesCaminando / cantidadFramesCaminando,
                altoFramesCaminando,
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

    @Override
    public void onAdded()
    {
        entity.getTransformComponent().setScaleOrigin(new Point2D(25, 25));
        entity.getViewComponent().addChild(texture);
        selectTypeOfHitBox(hitBox);
        texture.loopAnimationChannel(animIdle);
    }

    //Animacion de Movimiento
    public void selectTypeOfHitBox(int hitBox){
        if (hitBox == 11){
            collitionService.updateCollisionBox1x1(this.entity);
        } else if (hitBox == 12) {
            collitionService.updateCollisionBox1x2(this.entity);
        }else if (hitBox == 22){
            collitionService.updateCollisionBox2x2(this.entity);
        }else if(hitBox == 13){
            collitionService.updateCollisionBox1x3(this.entity);
        }
    }

    public void playDisabledBarrier(){
        texture.playAnimationChannel(animDisableBarrier);
    }

    public void playChangesPanel(){
        texture.loopAnimationChannel(animDisableBarrier);
    }

    private void move(Point2D dir, AnimationChannel channel, double scaleX) {
        if (isMoving) return;
        isMoving = true;

        double seconds = 0.7;
        double speedX = (TILE_SIZE / seconds) * dir.getX();
        double speedY = (TILE_SIZE / seconds) * dir.getY();

        PhysicsComponent physics = entity.getComponent(PhysicsComponent.class);
        physics.setLinearVelocity(speedX, speedY);

        texture.loopAnimationChannel(channel);
        entity.setScaleX(scaleX);

        FXGL.runOnce(() -> {
            physics.setLinearVelocity(0, 0);
            snapToGrid();
            texture.loopAnimationChannel(animIdle);
            isMoving = false;
        }, Duration.seconds(seconds));
    }

    public void moveUp()    { move(new Point2D(0, -1), animArriba, 1); }
    public void moveDown()  { move(new Point2D(0, 1), animAbajo, 1); }
    public void moveLeft()  { move(new Point2D(-1, 0), animWalk, -1); } // Flipped horizontally
    public void moveRight() { move(new Point2D(1, 0), animWalk, 1); }

    private void snapToGrid() {
        double x = Math.round(entity.getX() / TILE_SIZE) * TILE_SIZE;
        double y = Math.round(entity.getY() / TILE_SIZE) * TILE_SIZE;
        entity.setPosition(x, y);
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

        MusicService.playWeanpon();

        FXGL.getGameTimer().runOnceAfter(() -> {

            texture.loopAnimationChannel(animIdle);
            //FXGL.getAudioPlayer().stopAllMusic();
            MusicService.playBattle();

        }, Duration.seconds(animVelocity));
    }

    public void playSpecialAnimation(){
        texture.playAnimationChannel(animAtackEspecial);

        MusicService.playWeanpon();

        FXGL.getGameTimer().runOnceAfter(() -> {

            texture.loopAnimationChannel(animIdle);
            //FXGL.getAudioPlayer().stopAllMusic();
            MusicService.playBattle();

        }, Duration.seconds(1));
    }

    public void playDeatAnimation()
    {
        if (animMuerte != null){
            texture.playAnimationChannel(animMuerte);
        }
        FXGL.getGameTimer().runOnceAfter(() -> {

            texture.loopAnimationChannel(animIdle);

        }, Duration.seconds(1));
    }
}
