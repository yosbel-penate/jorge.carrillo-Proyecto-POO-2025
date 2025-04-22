package App.Game;

import com.almasb.fxgl.audio.Audio;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.dsl.FXGL;

public class MusicService {

    public static void playWeanponEnemy(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("armaEnemigos.wav"));
    }

    public static void playDeat(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("muerte.wav"));

    }

    public static void playItem(){
        FXGL.getAudioPlayer().playSound(FXGL.getAssetLoader().loadSound("item.wav"));

    }


    public static void playBattle(){
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("battle.wav"));
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
