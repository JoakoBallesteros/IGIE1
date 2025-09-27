/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class HozMortifera extends Arma {
    public HozMortifera() { super("Hoz Mortífera", 15); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(enemigo.nombre + " recibe veneno (-10 vida) y " + portador.nombre + " se cura +10.");
        enemigo.vida -= 10;
        portador.vida += 10;
    }
}