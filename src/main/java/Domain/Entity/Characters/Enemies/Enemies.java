package Domain.Entity.Characters.Enemies;


import Domain.Entity.Characters.Characters;

public class Enemies extends Characters {
    // Atributos comunes, no estáticos
    protected int life;
    protected int attack;
    protected String nameCharacter;
    protected int cantidadFrames;
    protected int anchoSpriteSheet;
    protected int altoSpriteSheet;
    protected int anchoSpriteSheetAtackBasic;
    protected int altoSpriteSheetAtackBasic;
    protected int cantidadFramesAtackBasic;

    // Constructor que inicializa todos los valores necesarios
    public Enemies(int life,
                   int attack,
                   String nameCharacter,
                   int cantidadFrames,
                   int anchoSpriteSheet,
                   int altoSpriteSheet,
                   int anchoSpriteSheetAtackBasic,
                   int altoSpriteSheetAtackBasic,
                   int cantidadFramesAtackBasic) {
        this.life = life;
        this.attack = attack;
        this.nameCharacter = nameCharacter;
        this.cantidadFrames = cantidadFrames;
        this.anchoSpriteSheet = anchoSpriteSheet;
        this.altoSpriteSheet = altoSpriteSheet;
        this.anchoSpriteSheetAtackBasic = anchoSpriteSheetAtackBasic;
        this.altoSpriteSheetAtackBasic = altoSpriteSheetAtackBasic;
        this.cantidadFramesAtackBasic = cantidadFramesAtackBasic;
    }

    // Getters y setters si los necesitas
    public int getLife() { return life; }
    public int getAttack() {return attack; }

}

