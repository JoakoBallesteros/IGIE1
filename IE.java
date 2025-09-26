

import java.util.Random;
import java.util.Scanner;

// ------------------- PERSONAJE -------------------
abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int fuerza;
    protected int defensa;
    protected Arma arma = null;
    protected int bendicion; // 0 a 100
    

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

    public void Daño(int danio) {
        int dañoReal = Math.max(0, danio - defensa);
        vida -= dañoReal;
        System.out.println(nombre + " recibe " + dañoReal + " de daño. Vida: " + Math.max(vida, 0));
    }

    public void atacar(Personaje enemigo) {
        int dañoTotal = fuerza + (arma != null ? arma.getDanioExtra() : 0);
        System.out.println(nombre + " ataca con fuerza total de " + dañoTotal + " usando " +
                (arma != null ? arma.getNombre() : "puños"));
        enemigo.Daño(dañoTotal);

        if (arma != null) {
            arma.usarEfectoEspecial(this, enemigo);
        }
    }

    public abstract void invocarArma();
    public abstract void decidirAccion(Personaje enemigo);

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", fuerza=" + fuerza + ", defensa=" + defensa +
                ", arma=" + (arma != null ? arma.getNombre() : "ninguna") +
                ", bendición=" + bendicion + "]";
    }
}

// ------------------- HEROE -------------------
class Heroe extends Personaje {
    public Heroe(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        super(nombre, vida, fuerza, defensa, bendicion);
    }

    @Override
    public void invocarArma() {
        BendicionCelestial bend = new BendicionCelestial();
        this.arma = bend.obtenerArma(bendicion);
        System.out.println(nombre + " invoca el arma: " + arma.getNombre());
    }



    @Override
    public void decidirAccion(Personaje enemigo) {

        if (bendicion >= 100) {
            System.out.println("Heroe ¡" + nombre + " desata el Castigo Bendito! ✨");
            System.out.println("Un rayo divino desciende desde los cielos...");
            int dano = (int)(this.vida * 0.5); // 50% de la vida ACTUAL del héroe
            enemigo.vida = Math.max(0, enemigo.vida - dano);
            System.out.println(enemigo.nombre + " recibe " + dano + " de daño directo del rayo divino. Vida: " + enemigo.vida);
            bendicion = 0; // consume la bendición
            return;
        }
        invocarArma();
        atacar(enemigo);
    }
}

// ------------------- VILLANO -------------------
class Villano extends Personaje {
    public Villano(String nombre, int vida, int fuerza, int defensa, int bendicion) {
        super(nombre, vida, fuerza, defensa, bendicion);
    }
    // -1: inactivo | >0: turnos restantes de casteo | 0: listo para ejecutar
    private int leviatanTurnos = -1;


    @Override
    public void invocarArma() {
        BendicionDelVacio maldicion = new BendicionDelVacio();
        this.arma = maldicion.obtenerArma(bendicion);
        System.out.println(nombre + " invoca el arma: " + arma.getNombre());
    }

    @Override
    public void decidirAccion(Personaje enemigo) {
    // Si ya está casteando Leviatán, avanzar el reloj
    if (leviatanTurnos > 0) {
        leviatanTurnos--;
        System.out.println(nombre + " está casteando Leviatán del Vacío... (" + leviatanTurnos + " turnos restantes)");
        // Mientras castea, NO ataca con armas normales
        return;
    }

    // Si el casteo terminó, ejecutar el ataque supremo
    if (leviatanTurnos == 0) {
        System.out.println("¡" + nombre + " desata a Leviatán del Vacío!");
        int dano = Math.max(1, enemigo.vida);       // 100% de la vida ACTUAL del héroe
        enemigo.vida = Math.max(0, enemigo.vida - dano);
        System.out.println(enemigo.nombre + " recibe " + dano + " de daño directo de Leviatán. Vida: " + enemigo.vida);
        leviatanTurnos = -1; // resetear
        return;
    }

    // Si no está casteando y tiene 100% de bendición, iniciar el casteo
    if (bendicion >= 100) {
        leviatanTurnos = 3; // tarda 3 turnos en castear
        bendicion = 0;      // consume la bendición
        System.out.println(nombre + " invoca a Leviatán del Vacío. Casteo iniciado (3 turnos).");
        return;
    }

    // Comportamiento normal: invocar arma y atacar
    invocarArma();
    atacar(enemigo);
}

}

// ------------------- ARMA -------------------
abstract class Arma {
    protected String nombre;
    protected int danioExtra;

    public Arma(String nombre, int danioExtra) {
        this.nombre = nombre;
        this.danioExtra = danioExtra;
    }

    public String getNombre() { return nombre; }
    public int getDanioExtra() { return danioExtra; }

    public abstract void usarEfectoEspecial(Personaje portador, Personaje enemigo);
}

// ------------------- ARMAS HEROE -------------------
class EspadaSimple extends Arma {
    public EspadaSimple() { super("Espada Simple", 5); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println("La espada simple no tiene efectos especiales.");
    }
}

class EspadaSagrada extends Arma {
    public EspadaSagrada() { super("Espada Sagrada", 10); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(portador.nombre + " se cura 10 de vida gracias a la espada sagrada.");
        portador.vida += 10;
    }
}

class EspadaCelestial extends Arma {
    public EspadaCelestial() { super("Espada Celestial", 15); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(portador.nombre + " se cura 20 de vida y gana +5 defensa.");
        portador.vida += 20;
        portador.defensa += 5;
    }
}

// ------------------- ARMAS VILLANO -------------------
class HozOxidada extends Arma {
    public HozOxidada() { super("Hoz Oxidada", 5); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println("La hoz oxidada no tiene efectos especiales.");
    }
}

class HozVenenosa extends Arma {
    public HozVenenosa() { super("Hoz Venenosa", 10); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(enemigo.nombre + " ha sido envenenado (-5 de vida por turno).");
        enemigo.vida -= 5;
    }
}

class HozMortifera extends Arma {
    public HozMortifera() { super("Hoz Mortífera", 15); }
    @Override
    public void usarEfectoEspecial(Personaje portador, Personaje enemigo) {
        System.out.println(enemigo.nombre + " recibe veneno (-10 vida) y " + portador.nombre + " se cura +10.");
        enemigo.vida -= 10;
        portador.vida += 10;
    }
}

// ------------------- BENDICIONES -------------------
class BendicionCelestial {
    public Arma obtenerArma(int porcentaje) {
        if (porcentaje < 40) return new EspadaSimple();
        else if (porcentaje < 70) return new EspadaSagrada();
        else return new EspadaCelestial();
    }
}

class BendicionDelVacio {
    public Arma obtenerArma(int porcentaje) {
        if (porcentaje < 40) return new HozOxidada();
        else if (porcentaje < 70) return new HozVenenosa();
        else return new HozMortifera();
    }
}

// ------------------- MAIN -------------------
public class IE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        // Crear héroe
        System.out.print("Ingrese nombre del héroe: ");
        String nombreHeroe = sc.nextLine();
        Heroe heroe = new Heroe(nombreHeroe, 100, 20, 5, rnd.nextInt(101));

        // Crear villano
        System.out.print("Ingrese nombre del villano: ");
        String nombreVillano = sc.nextLine();
        Villano villano = new Villano(nombreVillano, 100, 20, 5, rnd.nextInt(101));

        // Mostrar estado inicial
        System.out.println("\n⚔️ ¡Comienza la batalla! ⚔️");
        System.out.println(heroe);
        System.out.println(villano);
        System.out.println();


        if (heroe.bendicion >= 100) {
            System.out.println("Heroe " + heroe.nombre + " puede usar Castigo Bendito!");
        }
        if (villano.bendicion >= 100) {
            System.out.println(" Villano " + villano.nombre + " puede usar Leviatán del Vacío!");
        }
        System.out.println();


        // Simulación de batalla
        while (heroe.estaVivo() && villano.estaVivo()) {
            heroe.decidirAccion(villano);
            if (!villano.estaVivo()) break;
            villano.decidirAccion(heroe);
            System.out.println();
        }

        // Resultado
        if (heroe.estaVivo()) {
            System.out.println(heroe.nombre + " ha ganado la batalla!");
        } else {
            System.out.println(villano.nombre + " ha ganado la batalla!");
        }

        sc.close();
    }
}
