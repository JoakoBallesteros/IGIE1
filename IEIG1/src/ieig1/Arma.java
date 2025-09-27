/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
abstract class Arma {
    protected String nombre;
    protected int danioExtra;

    public Arma(String nombre, int danioExtra) {
        this.nombre = nombre;
        this.danioExtra = danioExtra;
    }

    public String getNombre() { return nombre; }
    public int getDanioExtra() { return danioExtra; }

    public abstract void usarEfectoEspecial(Personaje portador, Personaje enemigo);
}

// ------------------- ARMAS HEROE -------------------
class EspadaSimple extends Arma {
    public EspadaSimple() { super("Espada Simple", 5); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println("La espada simple no tiene efectos especiales.");
    }
}

class EspadaSagrada extends Arma {
    public EspadaSagrada() { super("Espada Sagrada", 10); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(portador.nombre + " se cura 10 de vida gracias a la espada sagrada.");
        portador.vida += 10;
    }
}

class EspadaCelestial extends Arma {
    public EspadaCelestial() { super("Espada Celestial", 15); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(portador.nombre + " se cura 20 de vida y gana +5 defensa.");
        portador.vida += 20;
        portador.defensa += 5;
    }
}
