package App.Services;

public class Acertijo {
    private String imagen;
    private String respuestaCorrecta;

    public Acertijo(String imagen, String respuestaCorrecta) {
        this.imagen = imagen;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getImagen() {
        return imagen;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}

