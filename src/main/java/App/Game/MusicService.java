package App.Game;

import com.almasb.fxgl.dsl.FXGL;

public class MusicService {

    public static void playLevel1Music()
    {
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("loading.wav"));
    }
    public static void stopMusic()
    {
        FXGL.getAudioPlayer().stopAllMusic();
    }
    public static void battleMusic()
    {
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("battle.wav"));
    }
}
