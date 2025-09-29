/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede y Lucas
 */
class Heroe extends Personaje {
    // último daño del Castigo Bendito para el reporte
    private int ultimoDanoCastigo = 0;

    public Heroe(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        super(nombre, vida, fuerza, defensa, bendicion);
    }

    public int getUltimoDanoCastigo() {
        return ultimoDanoCastigo;
    }

    @Override
    public void invocarArma() {
        BendicionCelestial bend = new BendicionCelestial();
        this.arma = bend.obtenerArma(bendicion);
        System.out.println(nombre + " invoca el arma: " + arma.getNombre());
        // registrar arma para el reporte
        registrarArmaInvocada(arma.getNombre());
    }

  @Override
public void decidirAccion(Personaje enemigo) {
    // 1) Si puede usar el especial, lo usa y termina el turno (como antes)
    if (bendicion >= 100) {
        System.out.println("Heroe ¡" + nombre + " desata el Castigo Bendito! ✨");
        System.out.println("Un rayo divino desciende desde los cielos...");
        int dano = Math.max(1, (int)(this.vida * 0.5)); // 50% de vida ACTUAL
        enemigo.vida = Math.max(0, enemigo.vida - dano);
        System.out.println(enemigo.nombre + " recibe " + dano + " de daño directo del rayo divino. Vida: " + enemigo.vida);

        // Si tenés reporte: guarda daño/contador (si no, podés comentar estas 2 líneas)
        try { registrarSupremo("Castigo Bendito"); } catch (Exception ignored) {}
        try { this.ultimoDanoCastigo = dano; } catch (Exception ignored) {}

        bendicion = 0; // consume la bendición
        return;        // termina el turno
    }

    // 2) Si no hay especial, sigue el flujo normal de siempre
    invocarArma();
    atacar(enemigo);
}


    //  Helper para el ataque especial 
    private void usarCastigoBendito(Personaje enemigo) {
        System.out.println("Heroe ¡" + nombre + " desata el Castigo Bendito! ✨");
        System.out.println("Un rayo divino desciende desde los cielos...");

        int dano = Math.max(1, (int)(this.vida * 0.5)); // 50% de la vida actual
        enemigo.vida = Math.max(0, enemigo.vida - dano);
        System.out.println(enemigo.nombre + " recibe " + dano +
                           " de daño directo del rayo divino. Vida: " + enemigo.vida);

        // registrar para el reporte
        ultimoDanoCastigo = dano;
        registrarSupremo("Castigo Bendito");

        // consumir la bendición
        bendicion = 0;
    }
}