/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class EspadaSimple extends Arma {
    public EspadaSimple() { super("Espada Simple", 5); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println("La espada simple no tiene efectos especiales.");
    }
}