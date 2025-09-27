/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class HozOxidada extends Arma {
    public HozOxidada() { super("Hoz Oxidada", 5); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println("La hoz oxidada no tiene efectos especiales.");
    }
}