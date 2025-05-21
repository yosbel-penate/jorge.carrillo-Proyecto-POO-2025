package App.Services;

import com.almasb.fxgl.dsl.FXGL;

public class MusicService {

    public static void playWeanponEnemy(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("armaEnemigos.wav"));
    }

    public static void playDeat(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("muerte.wav"));

    }

    public static void playMainMenu(){
        FXGL.getAudioPlayer().playMusic(FXGL.getAssetLoader().loadMusic("menu.wav"));
    }

    public static void stopMainMenu(){
        FXGL.getAudioPlayer().stopMusic(FXGL.getAssetLoader().loadMusic("menu.wav"));
    }

    public static void playItem(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("item.wav"));

    }

    public static void playChangeCharacter(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("cambiarPersonaje.wav"));

    }

    public static void playKey(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("key.wav"));
    }

    public static void playCoin(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("coin.wav"));
    }

    public static void playBattle(){
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("battle.wav"));
    }

    public static void playPanel(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("panel.wav"));
    }

    public static void playWeanpon(){FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("arma.wav"));}

    public static void playLevel1Music() {
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("loading.wav"));
    }

    public static void stopLevel1(){
        FXGL.getAudioPlayer().stopMusic(FXGL.getAssetLoader().loadMusic("loading.wav"));
    }

    public static void battleMusic() {
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("battle.wav"));
    }

    public static void stopBattleMusic(){
        FXGL.getAudioPlayer().stopMusic(FXGL.getAssetLoader().loadMusic("battle.wav"));
    }
}
