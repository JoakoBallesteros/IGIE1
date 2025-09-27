/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */
class BendicionCelestial {
    public Arma obtenerArma(int porcentaje) {
        if (porcentaje < 40) return new EspadaSimple();
        else if (porcentaje < 70) return new EspadaSagrada();
        else return new EspadaCelestial();
    }
}

