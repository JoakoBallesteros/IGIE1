/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class Villano extends Personaje {
    public Villano(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        super(nombre, vida, fuerza, defensa, bendicion);
    }
    
    private int leviatanTurnos = -1;


    @Override
    public void invocarArma() {
        BendicionDelVacio maldicion = new BendicionDelVacio();
        this.arma = maldicion.obtenerArma(bendicion);
        System.out.println(nombre + " invoca el arma: " + arma.getNombre());
    }

    @Override
    public void decidirAccion(Personaje enemigo) {
    
    if (leviatanTurnos > 0) {
        leviatanTurnos--;
        System.out.println(nombre + " está casteando Leviatán del Vacío... (" + leviatanTurnos + " turnos restantes)");
        
        return;
    }

   
    if (leviatanTurnos == 0) {
        System.out.println("¡" + nombre + " desata a Leviatán del Vacío!");
        int dano = Math.max(1, enemigo.vida);      
        enemigo.vida = Math.max(0, enemigo.vida - dano);
        System.out.println(enemigo.nombre + " recibe " + dano + " de daño directo de Leviatán. Vida: " + enemigo.vida);
        leviatanTurnos = -1; 
        return;
    }

    
    if (bendicion >= 100) {
        leviatanTurnos = 2; 
        bendicion = 0;      
        System.out.println(nombre + " invoca a Leviatán del Vacío. Casteo iniciados.");
        return;
    }

    
    invocarArma();
    atacar(enemigo);
}

}