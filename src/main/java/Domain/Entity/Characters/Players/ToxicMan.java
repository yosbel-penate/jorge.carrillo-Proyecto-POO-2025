package Domain.Entity.Characters.Players;

public class ToxicMan extends Players{
    public ToxicMan()
    {
        this.name = "toxico";
        this.atack = 2;
        this.life = 10;
        this.cantidadFramesIdle = 4;
        this.cantidadFramesAtack = 4;
        this.cantidadFramesCaminando = 4;
        this.altoFrameAtack = 50;
        this.anchoFrameAtack = 272;
        this.altoFramesIdle = 50;
        this.anchoFramesCaminando = 265;
        this.anchoFramesIdle = 245;
        this.altoFramesCaminando = 60;
        this.cantidaFramesMuerte = 4;
        this.altoFramesMuerte = 50;
        this.anchoFramesMuerte = 205;
        this.cantidadFramesAtackSpecial = 12;
        this.altoFramesAtackSpecial = 50;
        this.anchoFramesAtackSpecial = 1500;
        this.hitBox = 11;
    }
}
