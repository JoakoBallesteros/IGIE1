/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class EspadaSagrada extends Arma {
    public EspadaSagrada() { super("Espada Sagrada", 10); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(portador.nombre + " se cura 10 de vida gracias a la espada sagrada.");
        portador.vida += 10;
    }
}
