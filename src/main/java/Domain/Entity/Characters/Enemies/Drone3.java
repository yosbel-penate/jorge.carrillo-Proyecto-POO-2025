package Domain.Entity.Characters.Enemies;

public class Drone3 extends Enemies {
    public static int life = 5;
    public static int atack = 5;
    public static final String nameCharacter = "droid3";
    public static final int cantidadFrames = 4;
    public static final int anchoSpriteSheet = 505;
    public static final int altoSpriteSheet = 50;
    public static final int altoSpriteSheetAtackBasic = 50;
    public static final int anchoSpriteSheetAtackBasic = 1200;
    public static final int cantidadFramesAtackBasic = 9;
    public static final int cantidadFramesMuerte = 10;
    public static final int altoFrameMuerte = 51;
    public static final int anchoFrameMuerte = 900;

    public Drone3() {
        super(
                5,           // life
                5,               // attack
                "droid3",         // nameCharacter
                4,               // cantidadFrames
                505,             // anchoSpriteSheet
                50,              // altoSpriteSheet
                50,             // anchoSpriteSheetAtackBasic
                50,              // altoSpriteSheetAtackBasic
                12               // cantidadFramesAtackBasic
        );
    }
}
