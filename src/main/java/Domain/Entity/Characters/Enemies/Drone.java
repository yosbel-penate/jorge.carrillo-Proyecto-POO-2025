package Domain.Entity.Characters.Enemies;

public class Drone extends Enemies {

    public Drone() {
        super(
                5,           // life
                2,               // attack
                "droid",         // nameCharacter
                4,               // cantidadFrames
                200,             // anchoSpriteSheet
                50,              // altoSpriteSheet
                938,             // anchoSpriteSheetAtackBasic
                50,              // altoSpriteSheetAtackBasic
                12               // cantidadFramesAtackBasic
        );
    }
}
