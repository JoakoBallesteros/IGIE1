/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class HozVenenosa extends Arma {
    public HozVenenosa() { super("Hoz Venenosa", 10); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(enemigo.nombre + " ha sido envenenado (-5 de vida por turno).");
        enemigo.vida -= 5;
    }
}
