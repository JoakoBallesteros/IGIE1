/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int fuerza;
    protected int defensa;
    protected Arma arma = null;
    protected int bendicion; // 0 a 100
    

    public Personaje(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.bendicion = bendicion;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void Daño(int danio) {
        int dañoReal = Math.max(0, danio - defensa);
        vida -= dañoReal;
        System.out.println(nombre + " recibe " + dañoReal + " de daño. Vida: " + Math.max(vida, 0));
    }

    public void atacar(Personaje enemigo) {
        int dañoTotal = fuerza + (arma != null ? arma.getDanioExtra() : 0);
        System.out.println(nombre + " ataca con fuerza total de " + dañoTotal + " usando " +
                (arma != null ? arma.getNombre() : "puños"));
        enemigo.Daño(dañoTotal);

        if (arma != null) {
            arma.usarEfectoEspecial(this, enemigo);
        }
    }

    public abstract void invocarArma();
    public abstract void decidirAccion(Personaje enemigo);

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", fuerza=" + fuerza + ", defensa=" + defensa +
                ", arma=" + (arma != null ? arma.getNombre() : "ninguna") +
                ", bendición=" + bendicion + "]";
    }
}