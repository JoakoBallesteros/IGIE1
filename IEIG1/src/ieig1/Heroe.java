/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class Heroe extends Personaje {
    public Heroe(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        super(nombre, vida, fuerza, defensa, bendicion);
    }

    @Override
    public void invocarArma() {
        BendicionCelestial bend = new BendicionCelestial();
        this.arma = bend.obtenerArma(bendicion);
        System.out.println(nombre + " invoca el arma: " + arma.getNombre());
    }



    @Override
    public void decidirAccion(Personaje enemigo) {

        if (bendicion >= 100) {
            System.out.println("Heroe ¡" + nombre + " desata el Castigo Bendito! ✨");
            System.out.println("Un rayo divino desciende desde los cielos...");
            int dano = (int)(this.vida * 0.5); // 50% de la vida ACTUAL del héroe
            enemigo.vida = Math.max(0, enemigo.vida - dano);
            System.out.println(enemigo.nombre + " recibe " + dano + " de daño directo del rayo divino. Vida: " + enemigo.vida);
            bendicion = 0; // consume la bendición
            return;
        }
        invocarArma();
        atacar(enemigo);
    }
}
