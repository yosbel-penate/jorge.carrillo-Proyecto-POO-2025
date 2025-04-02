package src.main.java.Elements;

// Clase Elements 
abstract class Elements { 
protected String nameElement; 
protected String typeElement; 
protected String textureElement; // Simulando Texture como String 
protected String animatedTexture; // Simulando Animation como String 
protected int dimensionX; 
protected int dimensionY;

public Elements(String nameElement, String typeElement, String textureElement, String animatedTexture, int dimensionX, int dimensionY) {
    this.nameElement = nameElement;
    this.typeElement = typeElement;
    this.textureElement = textureElement;
    this.animatedTexture = animatedTexture;
    this.dimensionX = dimensionX;
    this.dimensionY = dimensionY;
}

public abstract void effect();

}
