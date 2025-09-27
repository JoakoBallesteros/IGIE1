/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class EspadaCelestial extends Arma {
    public EspadaCelestial() { super("Espada Celestial", 15); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(portador.nombre + " se cura 20 de vida y gana +5 defensa.");
        portador.vida += 20;
        portador.defensa += 5;
    }
}
