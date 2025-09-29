/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ieig1;

import java.util.*;

abstract class Personaje {
    //  Estado base del personaje 
    protected String nombre;
    protected int vida;
    protected int fuerza;
    protected int defensa;
    protected Arma arma = null;
    protected int bendicion; // 0..100

    //  Para el reporte final 
    protected Map<String, Integer> armasInvocadas = new LinkedHashMap<>();
    protected int supremosUsados = 0;
    protected List<String> logEspeciales = new ArrayList<>();

    public Personaje(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.bendicion = bendicion;
    }

    public boolean estaVivo() { 
        return vida > 0; 
    }

    public void recibirDanio(int danio) {
        int real = Math.max(0, danio - defensa);
        vida -= real;
        System.out.println(nombre + " recibe " + real + " de daño. Vida: " + Math.max(vida, 0));
    }

    public void atacar(Personaje enemigo) {
        int total = fuerza + (arma != null ? arma.getDanioExtra() : 0);
        System.out.println(
            nombre + " ataca con fuerza total de " + total + " usando " +
            (arma != null ? arma.getNombre() : "puños")
        );
        enemigo.recibirDanio(total);

        if (arma != null) {
            arma.usarEfectoEspecial(this, enemigo);
        }
    }

    //  Helpers para el reporte 
    protected void registrarArmaInvocada(String armaNombre) {
        armasInvocadas.merge(armaNombre, 1, Integer::sum);
    }

    protected void registrarSupremo(String descripcion) {
        supremosUsados++;
        logEspeciales.add(descripcion);
        
    }

    //  Getters usados por IEIG1.generarReporteFinal 
    public int getSupremosUsados() { return supremosUsados; }
    public Map<String, Integer> getArmasInvocadas() { return armasInvocadas; }
    public List<String> getLogEspeciales() { return logEspeciales; }
    public String getNombre() { return nombre; }
    public int getVida() { return Math.max(vida, 0); }

    //  Contrato para las subclases 
    public abstract void invocarArma();
    public abstract void decidirAccion(Personaje enemigo);

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", fuerza=" + fuerza + ", defensa=" + defensa +
               ", arma=" + (arma != null ? arma.getNombre() : "ninguna") +
               ", bendición=" + bendicion + "]";
    }
}