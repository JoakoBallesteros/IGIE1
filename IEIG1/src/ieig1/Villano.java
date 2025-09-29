/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

class Villano extends Personaje {
    public Villano(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        super(nombre, vida, fuerza, defensa, bendicion);
    }

    private int leviatanTurnos = -1;      // -1=no, >0 casteando, 0 lanzar
    private int ultimoDanoLeviatan = 0;   

    public boolean leviatanInterrumpido() { return leviatanTurnos > 0; }
    public int getUltimoDanoLeviatan() { return ultimoDanoLeviatan; }

    @Override
    public void invocarArma() {
        BendicionDelVacio maldicion = new BendicionDelVacio();
        this.arma = maldicion.obtenerArma(bendicion);
        System.out.println(nombre + " invoca el arma: " + arma.getNombre());
        registrarArmaInvocada(arma.getNombre()); // (para reporte de armas)
    }

    @Override
public void decidirAccion(Personaje enemigo) {

    // 1) Si está casteando, avanza y termina turno
    if (leviatanTurnos > 0) {
        leviatanTurnos--;
        System.out.println(nombre + " está casteando Leviatán del Vacío... (" + leviatanTurnos + " turnos restantes)");
        return;
    }

    // 2) Si terminó el casteo, lanza y termina turno
    if (leviatanTurnos == 0) {
        System.out.println("¡" + nombre + " desata a Leviatán del Vacío!");
        int dano = Math.max(1, enemigo.vida); // daño igual a vida actual del enemigo
        enemigo.vida = Math.max(0, enemigo.vida - dano);
        System.out.println(enemigo.nombre + " recibe " + dano + " de daño directo de Leviatán. Vida: " + enemigo.vida);

        // Si tenés reporte: guarda contador/daño (si no, estas líneas se ignoran)
        try { registrarSupremo("Leviatán del Vacío"); } catch (Exception ignored) {}
        try { this.ultimoDanoLeviatan = dano; } catch (Exception ignored) {}

        leviatanTurnos = -1; // limpia estado
        return;
    }

    // 3) Si puede iniciar el especial, inicia el casteo y termina turno
    if (bendicion >= 100) {
        leviatanTurnos = 2; // 2→1→0→lanza
        bendicion = 0;      // consume bendición
        System.out.println(nombre + " invoca a Leviatán del Vacío. Casteo iniciado.");
        return;
    }

    // 4) Flujo normal de siempre
    invocarArma();
    atacar(enemigo);
}

}